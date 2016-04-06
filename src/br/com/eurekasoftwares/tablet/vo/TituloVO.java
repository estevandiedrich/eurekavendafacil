/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eurekasoftwares.tablet.vo;

import java.util.Date;
import java.util.Hashtable;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 *
 * @author Estevan
 */
@Table(name="TITULO")
@XStreamAlias("a")
public class TituloVO implements Comparable<TituloVO>
{
    public TituloVO()
    {
        super();
        this.dataEmissao = new Date();
    }
    public TituloVO(@SuppressWarnings("rawtypes") Hashtable hashtable)
    {
        this.numeroTitulo = (String)hashtable.get(ITituloVO.NUMERO_TITULO);
        this.customerCod = (String)hashtable.get(ITituloVO.CUSTOMER_COD);
        this.dataEmissao = new Date(Long.parseLong((String)hashtable.get(ITituloVO.DATA_EMISSAO)));
        this.dataVencimento = new Date(Long.parseLong((String)hashtable.get(ITituloVO.DATA_VENCIMENTO)));
        try
        {
            this.valor = Float.parseFloat((String)hashtable.get(ITituloVO.VALOR));
        }
        catch(NumberFormatException numberFormatException)
        {
            this.valor = 0.0f;
        }
    }
    public String getXML()
    {
        StringBuffer xml = new StringBuffer();
        xml.append("<TituloVO>");
        xml.append("<customerCod>");
        xml.append(this.getCustomerCod());
        xml.append("</customerCod>");
        xml.append("<numeroTitulo>");
        xml.append(this.getNumeroTitulo());
        xml.append("</numeroTitulo>");
        xml.append("<dataVencimento>");
        xml.append(this.getDataVencimento());
        xml.append("</dataVencimento>");
        xml.append("<valor>");
        xml.append(this.getValor());
        xml.append("</valor>");
        xml.append("</TituloVO>");
        return xml.toString();
    }
    @PrimaryKey
	@Column(name="NUMEROTITULO")
    @XStreamAlias("c")
    private String numeroTitulo;
    @Column(name="CLIENTE")
    @XStreamAlias("b")
    private String customerCod;
    @Column(name="DATAEMISSAO")
    @XStreamAlias("d")
    private Date dataEmissao;
    @Column(name="DATAVENCIMENTO")
    @XStreamAlias("e")
    private Date dataVencimento;
    @Column(name="VALOR")
    @XStreamAlias("f")
    private float valor;
    @Column(name="COBRADO")
    @XStreamOmitField
    private String cobrado = "N";
    @Column(name="SINCRONIZADO")
    @XStreamOmitField
    private String sincronizado = "N";
    
    @Override
	public int compareTo(TituloVO rhs) {
		// TODO Auto-generated method stub
		Date date1 = this.getDataEmissao();
		Date date2 = rhs.getDataEmissao();
		return date1.compareTo(date2);
	}
    
    public String getCustomerCod() {
        return customerCod;
    }

    public void setCustomerCod(String customerCod) {
        this.customerCod = customerCod;
    }

    public String getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    public Date getDataEmissao() {
    	return dataEmissao==null?new Date():dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
	public String getCobrado() {
		return cobrado;
	}
	public void setCobrado(String cobrado) {
		this.cobrado = cobrado;
	}
	public String getSincronizado() {
		return sincronizado;
	}
	public void setSincronizado(String sincronizado) {
		this.sincronizado = sincronizado;
	}
}
