package br.com.eurekasoftwares.tablet.dao.impl;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;
import br.com.eurekasoftwares.tablet.vo.ProductFromPedidoVO;

public class ProductFromPedidoDAO extends DroidDao<ProductFromPedidoVO, Long> {
	public ProductFromPedidoDAO(TableDefinition<ProductFromPedidoVO> tableDefinition, SQLiteDatabase database)
	{
		super(ProductFromPedidoVO.class, tableDefinition, database);
	}
}
