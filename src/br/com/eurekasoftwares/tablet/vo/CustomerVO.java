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
@Table(name="CLIENTE")
public class CustomerVO implements Comparable<CustomerVO>{
	@PrimaryKey
	@Column(name="NOMEFANTASIA")
	private String nomeFantasia;
	@Column(name="COD")
    private String cod;
	@Column(name="NOME")
    private String nome;
	@Column(name="CIDADE")
    private String cidade;
	@Column(name="BAIRRO")
    private String bairro;
	@Column(name="RUA")
    private String rua;
	@Column(name="NUMERO")
    private String numero;
	@Column(name="CEP")
    private String cep;
	@Column(name="CONTATO")
    private String contato;
	@Column(name="CGC")
    private String cgc;
	@Column(name="IE")
    private String ie;
	@Column(name="BLOQUEADO")
    private String bloqueado;

    public CustomerVO(@SuppressWarnings("rawtypes") Hashtable hashtable)
    {
        this.cod = (String)hashtable.get(ICustomerVO.COD);
        this.bairro = (String)hashtable.get(ICustomerVO.BAIRRO);
        this.cidade = (String)hashtable.get(ICustomerVO.CIDADE);
        this.contato = (String)hashtable.get(ICustomerVO.CONTATO);
        this.nomeFantasia = (String)hashtable.get(ICustomerVO.NOME_FANTASIA);
        this.numero = (String)hashtable.get(ICustomerVO.NUMERO);
        this.rua = (String)hashtable.get(ICustomerVO.RUA);
        this.cep = (String)hashtable.get(ICustomerVO.CEP);
        this.cgc = (String)hashtable.get(ICustomerVO.CGC);
        this.ie = (String)hashtable.get(ICustomerVO.IE);
        this.bloqueado = (String)hashtable.get(ICustomerVO.BLOQUEADO);
    }
    public CustomerVO()
    {
        super();
    }
    
    @Override
	public int compareTo(CustomerVO rhs) {
    	String cod1 = this.getNomeFantasia();
		String cod2 = rhs.getNomeFantasia();
		return cod1.compareTo(cod2);
    }

	/**
     * @return the nomeFantasia
     */
    public String getNomeFantasia() {
        return nomeFantasia==null?"":nomeFantasia;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
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
     * @return the cidade
     */
    public String getCidade() {
        return cidade==null?"":cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro==null?"":bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero==null?"":numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the contato
     */
    public String getContato() {
        return contato==null?"":contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(String contato) {
        this.contato = contato;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua==null?"":rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome==null?"":nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep==null?"":cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the cgc
     */
    public String getCgc() {
        return cgc==null?"":cgc;
    }

    /**
     * @param cgc the cgc to set
     */
    public void setCgc(String cgc) {
        this.cgc = cgc;
    }

    /**
     * @return the ie
     */
    public String getIe() {
        return ie==null?"":ie;
    }

    /**
     * @param ie the ie to set
     */
    public void setIe(String ie) {
        this.ie = ie;
    }
    
    public String getBloqueado()
    {
        return this.bloqueado==null?"":bloqueado;
    }
    
    public void setBloqueado(String bloqueado)
    {
        this.bloqueado = bloqueado;
    }
}
