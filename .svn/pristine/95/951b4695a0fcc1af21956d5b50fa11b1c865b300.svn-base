package br.com.eurekasoftwares.tablet.vo;

import java.util.Hashtable;
import java.util.Random;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.ForeignKey;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Table(name="PRODUCTFROMPEDIDO")
@XStreamAlias("j")
public class ProductFromPedidoVO  implements Comparable<ProductFromPedidoVO>{
	
	@PrimaryKey
	@Column(name="ID")
	@XStreamOmitField
	private long id;
	@Column(name="COD")
	@XStreamAlias("k")
    private String cod;
	@ForeignKey(tableReference="PEDIDO", onDeleteCascade=true, columnReference="CODIGO")
	@Column(name="CODIGOPEDIDO")
	@XStreamOmitField
	private long codigoPedido;
	@Column(name="DESCRICAO")
	@XStreamOmitField
    private String descricao;
	@Column(name="URGENTE")
	@XStreamAlias("l")
    private String urgente;
	@Column(name="DESCONTOCONCEDIDO")
	@XStreamAlias("m")
    private int descontoConcedido;
	@Column(name="QUANTIDADEVENDA")
	@XStreamAlias("n")
    private int quantidadeVenda;
	@Column(name="QUANTIDADETROCA")
	@XStreamAlias("o")
    private float quantidadeTroca;
	@Column(name="PRECO")
	@XStreamAlias("p")
	private String preco;
	@Column(name="IDX")
	@XStreamOmitField
	private int idx;
	@Column(name="TOTALITEM")
	@XStreamOmitField
	private float totalItem;
	@Column(name="FECHADO")
	@XStreamOmitField
	private String fechado;
	
    public ProductFromPedidoVO()
    {
    	this.id = new Random().nextInt(999999999);
    	this.idx = 0;
        this.cod = "";
        this.descricao = "";
        this.urgente = "N";
        this.fechado = "N";
        this.preco = "0";
        this.descontoConcedido = 0;
        this.quantidadeTroca = 0.0f;
        this.quantidadeVenda = 0;
    }
    public ProductFromPedidoVO(@SuppressWarnings("rawtypes") Hashtable hashtable) 
    {
        this.cod = (String)hashtable.get(IProductVO.COD);
        this.descricao = (String)hashtable.get(IProductVO.DESCRICAO);
        this.urgente = (String)hashtable.get(IProductVO.URGENTE);
        this.descontoConcedido = Integer.parseInt((String)hashtable.get(IProductVO.DESCONTO_CONCEDIDO));
        this.quantidadeVenda = Integer.parseInt((String)hashtable.get(IProductVO.QUANTIDADE_VENDA));
        this.quantidadeTroca = Float.parseFloat((String)hashtable.get(IProductVO.QUANTIDADE_TROCA));
    }
    @Override
	public int compareTo(ProductFromPedidoVO rhs) {
		// TODO Auto-generated method stub
    	if(rhs.idx > this.idx)
    	{
    		return -1;
    	}
    	else if(rhs.idx < this.idx)
    	{
    		return 1;
    	}
    	return 0;
    }
    public String getXML()
    {
        StringBuffer xml = new StringBuffer();
        xml.append("<produto>");
        xml.append("<cod>");
        xml.append(this.getCod());
        xml.append("</cod>");
        xml.append("<urgente>");
        xml.append(this.getUrgente());
        xml.append("</urgente>");
        xml.append("<descontoConcedido>");
        xml.append(this.getDescontoConcedido());
        xml.append("</descontoConcedido>");
        xml.append("<quantidadeVenda>");
        xml.append(this.getQuantidadeVenda());
        xml.append("</quantidadeVenda>");
        xml.append("<quantidadeTroca>");
        xml.append(this.getQuantidadeTroca());
        xml.append("</quantidadeTroca>");
        xml.append("<preco>");
        xml.append(this.getPreco());
        xml.append("</preco>");
        xml.append("</produto>");
        return xml.toString();
    }
    
    
    
    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public int getDescontoConcedido() {
        return descontoConcedido;
    }

    public void setDescontoConcedido(int descontoConcedido) {
        this.descontoConcedido = descontoConcedido;
    }

    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public float getQuantidadeTroca() {
        return quantidadeTroca;
    }

    public void setQuantidadeTroca(float quantidadeTroca) {
        this.quantidadeTroca = quantidadeTroca;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public long getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public float getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(float totalItem) {
		this.totalItem = totalItem;
	}
	public String getFechado() {
		return fechado;
	}
	public void setFechado(String fechado) {
		this.fechado = fechado;
	}
}
