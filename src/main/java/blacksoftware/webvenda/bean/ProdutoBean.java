package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Produto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ProdutoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ProdutoBean.class.getName());

    private DAO dao;
    private Produto produto;
    private List<Produto> produtos;

    @PostConstruct
    public void init() {
        dao = new DAO();
        produto = new Produto();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", produto);
            dao.save(Produto.class, produto);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/produto/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", produto);
            dao.delete(Produto.class, produto);
            produtos = dao.list(Produto.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/produto/list.xhtml?faces-redirect=true";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        LOG.info("");
        if (produtos == null) {
            produtos = dao.list(Produto.class);
        }
        return produtos;
    }

}
