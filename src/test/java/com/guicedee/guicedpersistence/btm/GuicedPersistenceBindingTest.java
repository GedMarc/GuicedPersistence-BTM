package com.guicedee.guicedpersistence.btm;

import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.logger.LogFactory;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

public class GuicedPersistenceBindingTest
{
	@Test
	public void testMe() throws InterruptedException
	{
		LogFactory.configureConsoleSingleLineOutput(Level.FINE);
		GuiceContext.inject();

	}
}
