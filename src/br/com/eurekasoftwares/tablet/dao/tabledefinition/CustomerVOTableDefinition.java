package br.com.eurekasoftwares.tablet.dao.tabledefinition;

import org.droidpersistence.dao.TableDefinition;

import br.com.eurekasoftwares.tablet.vo.CustomerVO;

public class CustomerVOTableDefinition extends TableDefinition<CustomerVO> {
	public CustomerVOTableDefinition()
	{
		super(CustomerVO.class);
	}
}
