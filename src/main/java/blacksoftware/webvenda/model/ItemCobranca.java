package blacksoftware.webvenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_cobranca")
public class ItemCobranca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "data_cobranca")
	private Date dataCobranca;
	private BigDecimal valor;
	@ManyToOne
	private Cobranca cobranca;

	public ItemCobranca() {
	}

	public ItemCobranca(Long id, Date dataCobranca, BigDecimal valor,
			Cobranca cobranca) {
		super();
		this.id = id;
		this.dataCobranca = dataCobranca;
		this.valor = valor;
		this.cobranca = cobranca;
	}

	public Cobranca getCobranca() {
		return cobranca;
	}

	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

	public Date getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(Date dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
