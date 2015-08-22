package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Ramo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RamoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(RamoBean.class.getName());

    private DAO dao;
    private Ramo ramo;
    private List<Ramo> ramos;

    @PostConstruct
    public void init() {
        dao = new DAO();
        ramo = new Ramo();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", ramo);
            dao.save(Ramo.class, ramo);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/ramo/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", ramo);
            dao.delete(Ramo.class, ramo);
            ramos = dao.list(Ramo.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/ramo/list.xhtml?faces-redirect=true";
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }

    public List<Ramo> getRamos() {
        LOG.info("");
        if (ramos == null) {
            ramos = dao.list(Ramo.class);
        }
        return ramos;
    }

}
