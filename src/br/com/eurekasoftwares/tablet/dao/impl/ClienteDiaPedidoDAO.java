package br.com.eurekasoftwares.tablet.dao.impl;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;
import br.com.eurekasoftwares.tablet.vo.ClienteDiaPedidoVO;

public class ClienteDiaPedidoDAO extends DroidDao<ClienteDiaPedidoVO,Long>{
	public ClienteDiaPedidoDAO(TableDefinition<ClienteDiaPedidoVO> tableDefinition, SQLiteDatabase database)
	{
		super(ClienteDiaPedidoVO.class, tableDefinition, database);
	}
}
