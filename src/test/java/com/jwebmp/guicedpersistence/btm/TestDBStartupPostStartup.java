package com.jwebmp.guicedpersistence.btm;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedpersistence.services.IAsyncStartup;

import javax.sql.DataSource;

public class TestDBStartupPostStartup
		implements IAsyncStartup
{
	@Inject
	public TestDBStartupPostStartup(@TestCustomPersistenceLoader PersistService ps, @TestCustomPersistenceLoader DataSource ds)
	{
		ps.start();
	}

}
