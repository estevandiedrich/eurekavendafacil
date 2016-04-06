package br.com.eurekasoftwares.tablet.dao.impl;

import org.droidpersistence.dao.DroidDao;
import org.droidpersistence.dao.TableDefinition;

import android.database.sqlite.SQLiteDatabase;
import br.com.eurekasoftwares.tablet.vo.LoginVO;

public class LoginDAO extends DroidDao<LoginVO, Long> {
	public LoginDAO(TableDefinition<LoginVO> tableDefinition, SQLiteDatabase database)
	{
		super(LoginVO.class, tableDefinition, database);
	}
}
