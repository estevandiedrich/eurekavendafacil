/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eurekasoftwares.tablet.vo;

import java.util.Random;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;


/**
 *
 * @author estevan
 */
@Table(name="CLIENTEPRODUTOQUANTIDADEVO")
public class ClienteProdutoQuantidadeVO {
	@PrimaryKey
	@Column(name="CHAVE")
	private Long chave = new Random().nextLong();
	@Column(name="CODCLIENTE")
    private String codCliente = "";
	@Column(name="CODPRODUTO")
    private String codProduto = "";
	@Column(name="QUANTIDADE")
    private float quantidade = 0.0f;
	
    public Long getChave() {
		return chave;
	}

	public void setChave(Long chave) {
		this.chave = chave;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
}