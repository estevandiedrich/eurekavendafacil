/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eurekasoftwares.tablet.vo;

import java.util.Hashtable;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

/**
 *
 * @author germano
 */
@Table(name="PRODUTO")
public class ProductVO implements Comparable<ProductVO>{
	@PrimaryKey
	@Column(name="COD")
    private String cod;
	@Column(name="DESCRICAO")
    private String descricao;
	@Column(name="UNIDADE")
    private String unidade;
	@Column(name="ICMS")
    private int icms;
	@Column(name="DESCTOMAX")
    private float desctoMax;
	@Column(name="PRECO")
    private float preco;
	@Column(name="QUANTIDADE")
    private float quantidade;
	@Column(name="URGENTE")
    private String urgente;
    
    public ProductVO()
    {
        super();
    }
    public ProductVO(@SuppressWarnings("rawtypes") Hashtable hashtable)
    {
        this.cod = (String)hashtable.get(IProductVO.COD);
        this.descricao = (String)hashtable.get(IProductVO.DESCRICAO);
        this.unidade = (String)hashtable.get(IProductVO.UNIDADE);
        this.icms = Integer.parseInt((String)hashtable.get(IProductVO.ICMS));
        this.desctoMax = Float.parseFloat((String)hashtable.get(IProductVO.DESCTO_MAX));
        this.preco = Float.parseFloat((String)hashtable.get(IProductVO.PRECO));
        this.quantidade = Float.parseFloat((String)hashtable.get(IProductVO.QUANTIDADE));
        this.urgente = (String)hashtable.get(IProductVO.URGENTE);
    }
    
	@Override
	public int compareTo(ProductVO rhs) {
		// TODO Auto-generated method stub
		String desc1 = this.getDescricao();
		String desc2 = rhs.getDescricao();
		return desc1.compareTo(desc2);
	}

    /**
     * @return the cod
     */
    public String getCod() {
        return cod==null?"":cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao==null?"":descricao;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }


    /**
     * @return the unidade
     */
    public String getUnidade() {
        return unidade==null?"":unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * @return the icms
     */
    public int getIcms() {
        return icms;
    }

    /**
     * @param icms the icms to set
     */
    public void setIcms(int icms) {
        this.icms = icms;
    }

    /**
     * @return the desctoMax
     */
    public float getDesctoMax() {
        return desctoMax;
    }

    /**
     * @param desctoMax the desctoMax to set
     */
    public void setDesctoMax(float desctoMax) {
        this.desctoMax = desctoMax;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getUrgente(){
        return urgente==null?"":urgente;
    }
    
    public void setUrgente(String urgente){
        this.urgente = urgente;
    }
}
