package br.com.eurekasoftwares.tablet.dao.impl;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;
import br.com.eurekasoftwares.tablet.vo.ClienteProdutoQuantidadeVO;

public class ClienteProdutoQuantidadeDAO extends DroidDao<ClienteProdutoQuantidadeVO, Long> {
	public ClienteProdutoQuantidadeDAO(TableDefinition<ClienteProdutoQuantidadeVO> tableDefinition, SQLiteDatabase database)
	{
		super(ClienteProdutoQuantidadeVO.class, tableDefinition, database);
	}
}
