package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Grupo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class GrupoBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(GrupoBean.class.getName());

    private DAO dao;
    private Grupo grupo;
    private List<Grupo> grupos;

    @PostConstruct
    public void init() {
        dao = new DAO();
        grupo = new Grupo();
    }
    
    @PreDestroy
    public void destroy() {
        dao.close();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", grupo);
            dao.save(Grupo.class, grupo);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/grupo/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", grupo);
            dao.delete(Grupo.class, grupo);
            grupos = dao.list(Grupo.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/grupo/list.xhtml?faces-redirect=true";
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupos() {
        LOG.info("");
        if (grupos == null) {
            grupos = dao.list(Grupo.class);
        }
        return grupos;
    }

}
