package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Movimentacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MovimentacaoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(MovimentacaoBean.class.getName());

    private DAO dao;
    private Movimentacao movimentacao;
    private List<Movimentacao> movimentacoes;

    @PostConstruct
    public void init() {
        dao = new DAO();
        movimentacao = new Movimentacao();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", movimentacao);
            dao.save(Movimentacao.class, movimentacao);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/movimentacao/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", movimentacao);
            dao.delete(Movimentacao.class, movimentacao);
            movimentacoes = dao.list(Movimentacao.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/movimentacao/list.xhtml?faces-redirect=true";
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public List<Movimentacao> getMovimentacoes() {
        LOG.info("");
        if (movimentacoes == null) {
            movimentacoes = dao.list(Movimentacao.class);
        }
        return movimentacoes;
    }

}
