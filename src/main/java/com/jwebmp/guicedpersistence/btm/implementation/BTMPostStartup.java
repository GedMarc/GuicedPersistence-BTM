package com.jwebmp.guicedpersistence.btm.implementation;

import bitronix.tm.TransactionManagerServices;
import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;
import com.jwebmp.logger.LogFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BTMPostStartup
		implements IGuicePostStartup<BTMPostStartup>
{
	private static final Logger log = LogFactory.getLog("BTMPostStartup");

	@Override
	public void postLoad()
	{
		if (TransactionManagerServices.isTransactionManagerRunning())
		{
			log.fine("Shutting down old BTM instance, container redeploy?");
			TransactionManagerServices.getTransactionManager()
			                          .shutdown();
		}
		log.fine("Booting BTM JTA Manager");
		if (!TransactionManagerServices.isTransactionManagerRunning())
		{
			try
			{
				TransactionManagerServices.getTransactionManager()
				                          .begin();
				TransactionManagerServices.getTransactionManager()
				                          .commit();
				log.fine("JTA Running : " + TransactionManagerServices.isTransactionManagerRunning());
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Unable to boot BTM", e);
			}
		}
	}

	@Override
	public Integer sortOrder()
	{
		return 25;
	}
}
