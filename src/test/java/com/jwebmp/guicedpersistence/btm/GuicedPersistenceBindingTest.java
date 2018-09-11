package com.jwebmp.guicedpersistence.btm;

import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.guicedpersistence.db.AsyncPostStartup;
import com.jwebmp.logger.LogFactory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class GuicedPersistenceBindingTest
{
	@Test
	public void testMe() throws InterruptedException
	{
		LogFactory.configureConsoleSingleLineOutput(Level.FINE);
		GuiceContext.inject();

		AsyncPostStartup.getExecutionService()
		                .awaitTermination(10, TimeUnit.MINUTES);

	}
}
