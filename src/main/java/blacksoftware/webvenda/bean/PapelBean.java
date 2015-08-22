package blacksoftware.webvenda.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Papel;
import blacksoftware.webvenda.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PapelBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(PapelBean.class.getName());

    private DAO dao;
    private Papel papel;
    private List<Papel> papeis;

    @PostConstruct
    public void init() {
        dao = new DAO();
        papel = new Papel();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", papel);
            dao.save(Papel.class, papel);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/papel/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", papel);
            dao.delete(Papel.class, papel);
            papeis = dao.list(Papel.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/papel/list.xhtml?faces-redirect=true";
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public List<Papel> getPapeis() {
        LOG.info("");
        if (papeis == null) {
            papeis = dao.list(Papel.class);
        }
        return papeis;
    }

}
