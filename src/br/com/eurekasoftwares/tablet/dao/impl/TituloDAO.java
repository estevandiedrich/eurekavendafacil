package br.com.eurekasoftwares.tablet.dao.impl;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;
import br.com.eurekasoftwares.tablet.vo.TituloVO;

public class TituloDAO extends DroidDao<TituloVO, String>{
	public TituloDAO(TableDefinition<TituloVO> tableDefinition, SQLiteDatabase database)
	{
		super(TituloVO.class, tableDefinition, database);
	}
}
