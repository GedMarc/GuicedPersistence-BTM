package com.jwebmp.guicedpersistence.btm;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedpersistence.services.IDBStartup;

import javax.sql.DataSource;

public class TestDBStartupPostStartup
		implements IDBStartup
{
	@Inject
	public TestDBStartupPostStartup(@TestCustomPersistenceLoader PersistService ps, @TestCustomPersistenceLoader DataSource ds)
	{
		ps.start();
	}

}
