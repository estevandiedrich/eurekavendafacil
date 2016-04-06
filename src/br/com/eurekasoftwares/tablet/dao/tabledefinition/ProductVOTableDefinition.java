package br.com.eurekasoftwares.tablet.dao.tabledefinition;

import org.droidpersistence.dao.TableDefinition;

import br.com.eurekasoftwares.tablet.vo.ProductVO;

public class ProductVOTableDefinition extends TableDefinition<ProductVO> {
	public ProductVOTableDefinition()
	{
		super(ProductVO.class);
	}
}
