package com.jwebmp.guicedpersistence.btm.implementation;

import bitronix.tm.TransactionManagerServices;
import com.jwebmp.guicedinjection.interfaces.IGuicePreDestroy;
import com.jwebmp.logger.LogFactory;

import java.util.logging.Logger;

public class BTMDestroyer
		implements IGuicePreDestroy<BTMDestroyer>
{
	private static final Logger log = LogFactory.getLog("BTMDestroyer");

	@Override
	public void onDestroy()
	{
		TransactionManagerServices.getTransactionManager()
		                          .shutdown();
		log.info("BTM Successfully Shutdown");
	}
}
