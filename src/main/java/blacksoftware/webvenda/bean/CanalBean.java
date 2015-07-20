package blacksoftware.webvenda.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Canal;

@Named
@RequestScoped
public class CanalBean implements Serializable {

    private static final long serialVersionUID = 4727964015555587349L;

    @Inject
    private DAO<Canal> dao;
    private Canal canal;
    private List<Canal> canais;

    @PostConstruct
    public void init() {
        if (canal == null) {
            canal = new Canal();
        }
    }

    public String salvar() {
        return "/canal/list.xhtml";
    }

    public String deletar() {
        return "/canal/list.xhtml";
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public List<Canal> getCanais() {
        if (canais == null) {
            canais = dao.list();
        }
        return canais;
    }

}
