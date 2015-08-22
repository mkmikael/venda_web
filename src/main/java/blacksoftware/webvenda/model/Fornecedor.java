package blacksoftware.webvenda.model;

import blacksoftware.webvenda.dao.DAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "fornecedor")
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "select f from Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findAllEagerGrupos", query = "select distinct f from Fornecedor f left join fetch f.grupos")
})
@XmlRootElement
public class Fornecedor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5099205718704627387L;
    @Id
    @GeneratedValue
    private Long id;
    private String codigo;
    private String nome;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Grupo> grupos;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String codigo, String nome) {
        super();
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Grupo> getGrupos() {
        if (grupos == null) {
            grupos= new ArrayList<>();
        }
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
}
