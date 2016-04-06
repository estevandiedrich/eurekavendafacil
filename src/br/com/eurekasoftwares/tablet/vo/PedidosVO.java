package br.com.eurekasoftwares.tablet.vo;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("x")
public class PedidosVO {
	@XStreamAlias("a")
	private List<PedidoVO> pedidos;
	public PedidosVO()
	{
		this.pedidos = new ArrayList<PedidoVO>();
	}
	public List<PedidoVO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoVO> pedidos) {
		this.pedidos = pedidos;
	}
	
}
