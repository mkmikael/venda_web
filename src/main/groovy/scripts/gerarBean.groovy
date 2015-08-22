def camelCase = {
    return it.substring(0, 1).toLowerCase() + it.substring(1)
}
def plural = {
    def ultimo = it.length() - 1
    if (it.charAt(ultimo) == 'l')
        it.substring(0, ultimo) + 'is'
    else if (it.charAt(ultimo) == 'r')
        it + 'es'
    else if (it.substring(ultimo - 2) == 'cao')
        it.substring(0, ultimo - 2) + 'coes'
    else
        it + 's'
}
def nome = 'Produto'
def template = ''' 
package blacksoftware.webvenda.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import blacksoftware.webvenda.dao.DAO;
import blacksoftware.webvenda.model.{2};
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class {2}Bean implements Serializable {

    private static final Logger LOG = Logger.getLogger({2}Bean.class.getName());

    private DAO<{2}> dao;
    private {2} {1};
    private List<{2}> {3};

    @PostConstruct
    public void init() {
        dao = new DAO<>({2}.class);
        {1} = new {2}();
    }

    public String salvar() {
        try {
            LOG.log(Level.INFO, "salvar {0}", {1});
            dao.salvar({1});
            JsfUtil.info("", "Salvo com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao salvar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao salvar", e);
        }
        return "/{1}/list.xhtml?faces-redirect=true";
    }

    public String deletar() {
        try {
            LOG.log(Level.INFO, "remover {0}", {1});
            dao.deletar({1});
            JsfUtil.info("", "Removido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("", "Ocorreu um erro ao deletar");
            LOG.log(Level.SEVERE, "Ocorreu um erro ao remover", e);
        }
        return "/{1}/list.xhtml?faces-redirect=true";
    }

    public {2} get{2}() {
        return {1};
    }

    public void set{2}({2} {1}) {
        this.{1} = {1};
    }

    public List<{2}> get{4}() {
        LOG.info("");
        {3} = dao.list();
        return {3};
    }

}

'''
def f = template.replace('{1}', camelCase(nome)).replace('{2}', nome).replace('{3}', plural(camelCase(nome))).replace('{4}', plural(nome))
println f
println 'end'