package blacksoftware.webvenda.bean;

import blacksoftware.webvenda.util.JsfUtil;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ClienteBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ClienteBean.class.getName());
    private DAO dao;
    private Cliente cliente;
    private List<Cliente> clientes;

    @PostConstruct
    public void init() {
        dao = new DAO();
        cliente = new Cliente();
    }

    @PreDestroy
    public void destroy() {
        dao.close();
    }
    
    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", cliente);
            dao.save(Cliente.class, cliente);
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/cliente/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", cliente);
            dao.delete(Cliente.class, cliente);
            clientes = dao.list(Cliente.class);
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/cliente/list.xhtml?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        LOG.info("");
        if (clientes == null) {
            clientes = dao.list(Cliente.class);
        }
        return clientes;
    }

}
