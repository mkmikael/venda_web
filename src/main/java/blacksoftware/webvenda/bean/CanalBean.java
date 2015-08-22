package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Canal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CanalBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CanalBean.class.getName());

    private DAO dao;
    private Canal canal;
    private List<Canal> canais;

    @PostConstruct
    public void init() {
        dao = new DAO();
        canal = new Canal();
    }

    @PreDestroy
    public void destroy() {
        dao.close();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", canal);
            dao.save(Canal.class, canal);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/canal/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", canal);
            dao.delete(Canal.class, canal);
            canais = dao.list(Canal.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/canal/list.xhtml?faces-redirect=true";
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public List<Canal> getCanais() {
        LOG.info("");
        if (canais == null) {
            canais = dao.list(Canal.class);
        }
        return canais;
    }

}
