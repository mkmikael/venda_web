package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.dao.DaoFactory;
import blacksoftware.webvenda.model.Fornecedor;
import blacksoftware.webvenda.model.Grupo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(FornecedorBean.class.getName());

    private Fornecedor fornecedor;
    private Grupo grupo;
    private List<Fornecedor> fornecedores;
    private DAO dao;

    @PostConstruct
    public void init() {
        dao = new DAO();
        fornecedor = new Fornecedor();
        grupo = new Grupo();
    }

    @PreDestroy
    public void destroy() {
        dao.close();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "grupos selecionados {0}", fornecedor.getGrupos());
            LOG.log(Level.INFO, "salvar {0}", fornecedor);
            dao.save(Fornecedor.class, fornecedor);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/fornecedor/list.xhtml";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", fornecedor);
            dao.delete(Fornecedor.class, fornecedor);
            fornecedores = dao.findListNamedQuery(Fornecedor.class, "Fornecedor.findAllEagerGrupos");
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/fornecedor/list.xhtml?faces-redirect=true";
    }

    public List<Fornecedor> getFornecedores() {
        LOG.info("");
        if (fornecedores == null) {
            fornecedores = dao.findListNamedQuery(Fornecedor.class, "Fornecedor.findAllEagerGrupos");
        }
        return fornecedores;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
