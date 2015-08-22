package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Cobranca;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CobrancaBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CobrancaBean.class.getName());

    private DAO dao;
    private Cobranca cobranca;
    private List<Cobranca> cobrancas;

    @PostConstruct
    public void init() {
        dao = new DAO();
        cobranca = new Cobranca();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", cobranca);
            dao.save(Cobranca.class, cobranca);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/cobranca/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", cobranca);
            dao.delete(Cobranca.class, cobranca);
            cobrancas = dao.list(Cobranca.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/cobranca/list.xhtml?faces-redirect=true";
    }

    public Cobranca getCobranca() {
        return cobranca;
    }

    public void setCobranca(Cobranca cobranca) {
        this.cobranca = cobranca;
    }

    public List<Cobranca> getCobrancas() {
        LOG.info("");
        if (cobrancas == null) {
            cobrancas = dao.list(Cobranca.class);
        }
        return cobrancas;
    }

}
