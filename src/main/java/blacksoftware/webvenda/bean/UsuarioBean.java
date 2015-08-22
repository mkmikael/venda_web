package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioBean.class.getName());

    private DAO dao;
    private Usuario usuario;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        dao = new DAO();
        usuario = new Usuario();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", usuario);
            dao.save(Usuario.class, usuario);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/usuario/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", usuario);
            dao.delete(Usuario.class, usuario);
            usuarios = dao.list(Usuario.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/usuario/list.xhtml?faces-redirect=true";
    }

    public List<Usuario> getUsuarios() {
        LOG.info("");
        if (usuarios == null) {
            usuarios = dao.list(Usuario.class);
        }
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
