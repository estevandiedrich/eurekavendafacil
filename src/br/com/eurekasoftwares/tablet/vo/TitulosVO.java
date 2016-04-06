package br.com.eurekasoftwares.tablet.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("x1")
public class TitulosVO {
	@XStreamAlias("y")
	private List<TituloVO> titulos;

	public List<TituloVO> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TituloVO> titulos) {
		this.titulos = titulos;
	}
}
