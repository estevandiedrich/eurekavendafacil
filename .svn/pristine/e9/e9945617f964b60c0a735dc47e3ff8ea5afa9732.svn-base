package br.com.eurekasoftwares.tablet.vo;

import java.util.Date;
import java.util.Random;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Table(name="PEDIDO")
@XStreamAlias("b")
public class PedidoBaseVO {
	@PrimaryKey
	@Column(name="CODIGO")
	@XStreamAlias("c")
	private long codigoPedido;
	@Column(name="SINCRONIZADO")
	@XStreamOmitField
    private String sincronizado;
	@Column(name="CUSTOMERCOD")
	@XStreamAlias("d")
    private String customerCod;
	@Column(name="DATAEMISSAO")
	@XStreamAlias("e")
	private Date dataEmissao;
    @Column(name="PRAZO")
    @XStreamAlias("f")
    private String prazo;
    @Column(name="TIPOPAGAMENTO")
    @XStreamAlias("g")
    private String tipoPagamento;
    @Column(name="URGENTE")
    @XStreamAlias("h")
    private String urgente;
    @Column(name="TOTAL")
    @XStreamOmitField
    private float total;
    
    public PedidoBaseVO()
    {
    	super();
    	this.sincronizado = "1";
    	this.total = 0.0f;
    	this.customerCod = "";
    	this.dataEmissao = new Date();
    	this.prazo = "";
    	this.tipoPagamento = "";
    	this.urgente = "";
    	this.codigoPedido = new Random().nextInt(9999);
    }
    public PedidoBaseVO(PedidoVO pedidoVO)
    {
    	this.setCodigoPedido(pedidoVO.getCodigoPedido());
    	this.setCustomerCod(pedidoVO.getCustomerCod());
    	this.setDataEmissao(pedidoVO.getDataEmissao());
    	this.setPrazo(pedidoVO.getPrazo());
    	this.setSincronizado(pedidoVO.getSincronizado());
    	this.setTipoPagamento(pedidoVO.getTipoPagamento());
    	this.setUrgente(pedidoVO.getUrgente());
    	this.setTotal(pedidoVO.getTotal());
    }
    public String getCustomerCod() {
        return customerCod==null?"":customerCod;
    }

    public void setCustomerCod(String customerCod) {
        this.customerCod = customerCod;
    }

    public String getUrgente() {
        return urgente==null?"":urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public String getPrazo() {
        return prazo==null?"":prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public Date getDataEmissao() {
        return dataEmissao==null?new Date():dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getTipoPagamento() {
        return tipoPagamento==null?"":tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public void setCodigoPedido(long codigoPedido)
    {
    	this.codigoPedido = codigoPedido;
    }
    public long getCodigoPedido() {
        return codigoPedido;
    }

    public String getSincronizado() {
        return sincronizado==null?"":sincronizado;
    }

    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }
    public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
