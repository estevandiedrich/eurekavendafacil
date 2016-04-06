/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eurekasoftwares.tablet.vo;

import java.util.Date;
import java.util.Random;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

/**
 *
 * @author estevan
 */
@Table(name="CLIENTEDIAPEDIDOVO")
public class ClienteDiaPedidoVO{
	@PrimaryKey
	@Column(name="CHAVE")
	private Long chave = new Random().nextLong();
	@Column(name="CODCLIENTE")
    private String codCliente;
	@Column(name="DIA")
    private Date dia;
	@Column(name="CODPEDIDO")
    private Long codPedido;

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
    
    public Long getCodPedido()
    {
        return this.codPedido;
    }
    
    public void setCodPedido(Long codPedido)
    {
        this.codPedido = codPedido;
    }

	public Long getChave() {
		return chave;
	}

	public void setChave(Long chave) {
		this.chave = chave;
	}
}
