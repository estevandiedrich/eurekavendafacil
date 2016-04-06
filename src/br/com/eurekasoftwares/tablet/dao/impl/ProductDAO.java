package br.com.eurekasoftwares.tablet.dao.impl;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;
import br.com.eurekasoftwares.tablet.vo.ProductVO;

public class ProductDAO extends DroidDao<ProductVO, String> {
	public ProductDAO(TableDefinition<ProductVO> tableDefinition, SQLiteDatabase database)
	{
		super(ProductVO.class, tableDefinition, database);
	}
}
