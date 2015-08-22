package blacksoftware.webvenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "select i from ItemPedido i")
})
public class ItemPedido implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5155213919662781638L;
    @Id
    @GeneratedValue
    private Long id;
    private int quantidade;
    private int bonificacao;
    private BigDecimal desconto;
    @Column(name = "preco_negociado")
    private BigDecimal precoNegociado;
    private BigDecimal total;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Pedido pedido;

    public ItemPedido() {
    }

    public ItemPedido(Long id, int quantidade, int bonificacao,
            BigDecimal desconto, BigDecimal precoNegociado, BigDecimal total,
            Produto produto, Pedido pedido) {
        super();
        this.id = id;
        this.quantidade = quantidade;
        this.bonificacao = bonificacao;
        this.desconto = desconto;
        this.precoNegociado = precoNegociado;
        this.total = total;
        this.produto = produto;
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getBonificacao() {
        return bonificacao;
    }

    public void setBonificacao(int bonificacao) {
        this.bonificacao = bonificacao;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getPrecoNegociado() {
        return precoNegociado;
    }

    public void setPrecoNegociado(BigDecimal precoNegociado) {
        this.precoNegociado = precoNegociado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
