package blacksoftware.webvenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import blacksoftware.webvenda.model.enums.Situacao;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "select c from Cliente c")
})
@XmlRootElement
public class Cliente implements Serializable {

    private static final long serialVersionUID = -2042814069224561906L;

    @Id
    @GeneratedValue
    private Long id;
    private String codigo;
    private String cnpj;
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Column(name = "razao_social")
    private String razaoSocial;
    private String endereco;
    private String bairro;
    private String referencia;
    private String cidade;
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private int rate;
    private BigDecimal limite;
    private String telefone;
    private String responsavel;
    @ManyToOne
    private Canal canal;
    @ManyToOne
    private Ramo ramo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Pedido> pedidos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Cobranca> cobrancas;

    public Cliente() {
    }

    public Cliente(Long id, String codigo, String cnpj, String nomeFantasia,
            String razaoSocial, String endereco, String bairro,
            String referencia, String cidade, String inscricaoEstadual,
            Situacao situacao, int rate, BigDecimal limite, String telefone,
            String responsavel, Canal canal, Ramo ramo, List<Pedido> pedidos) {
        super();
        this.id = id;
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.bairro = bairro;
        this.referencia = referencia;
        this.cidade = cidade;
        this.inscricaoEstadual = inscricaoEstadual;
        this.situacao = situacao;
        this.rate = rate;
        this.limite = limite;
        this.telefone = telefone;
        this.responsavel = responsavel;
        this.canal = canal;
        this.ramo = ramo;
        this.pedidos = pedidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }

    @XmlElementWrapper(name = "pedidos")
    @XmlElement(name = "pedido")
    public List<Pedido> getPedidos() {
        if (pedidos == null) pedidos = new ArrayList<>();
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Cobranca> getCobrancas() {
        return cobrancas;
    }

    public void setCobrancas(List<Cobranca> cobrancas) {
        this.cobrancas = cobrancas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.codigo);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

}
