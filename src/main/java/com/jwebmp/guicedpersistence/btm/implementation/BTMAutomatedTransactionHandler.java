package com.jwebmp.guicedpersistence.btm.implementation;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.jndi.BitronixContext;
import com.jwebmp.guicedpersistence.services.ITransactionHandler;
import com.jwebmp.logger.LogFactory;

import javax.persistence.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BTMAutomatedTransactionHandler
		implements ITransactionHandler
{
	/**
	 * Field log
	 */
	private static final Logger log = LogFactory.getLog("BTMAutomatedTransactionHandler");
	/**
	 * Field bitronixContext
	 */
	private static final BitronixContext bitronixContext = new BitronixContext();
	/**
	 * Field active
	 */
	private static boolean active = false;
	/**
	 * Field transactionExistedDuringBegin
	 */
	private boolean transactionExistedDuringBegin = false;

	public static boolean isActive()
	{
		return BTMAutomatedTransactionHandler.active;
	}

	public static void setActive(boolean active)
	{
		BTMAutomatedTransactionHandler.active = active;
	}

	/**
	 * What to do when beginning a transaction, always called
	 *
	 * @param createNew
	 * 		If create new was specified
	 * @param entityManager
	 * 		The entity manager associated
	 */
	@Override
	public void beginTransacation(boolean createNew, EntityManager entityManager)
	{
		BitronixContext bc = new BitronixContext();
		BitronixTransactionManager userTransaction;
		try
		{
			transactionExistedDuringBegin = transactionExists(entityManager);
			userTransaction = (BitronixTransactionManager) bc.lookup("java:comp/UserTransaction");
			if (createNew || !transactionExists(entityManager) || userTransaction.getStatus() != 0)
			{
				try
				{
					userTransaction = (BitronixTransactionManager) bc.lookup("java:comp/UserTransaction");
					userTransaction.begin();
				}
				catch (Exception e)
				{
					BTMAutomatedTransactionHandler.log.log(Level.WARNING, "Unable to automatically start the transaction", e);
				}
			}
		}
		catch (Exception e)
		{
			BTMAutomatedTransactionHandler.log.log(Level.WARNING, "Unable to automatically start the transaction", e);
		}
	}

	/**
	 * What to do when committing a transaction, always called
	 *
	 * @param createNew
	 * 		If the transaction already exists
	 * @param entityManager
	 * 		The entity manager associated
	 */
	@Override
	public void commitTransacation(boolean createNew, EntityManager entityManager)
	{
		if (createNew || transactionExists(entityManager))
		{
			try
			{
				BitronixTransactionManager userTransaction = (BitronixTransactionManager) BTMAutomatedTransactionHandler.bitronixContext.lookup("java:comp/UserTransaction");
				if (userTransaction.getStatus() == 0 && !transactionExistedDuringBegin)
				{
					userTransaction.commit();
				}
			}
			catch (Exception e)
			{
				BTMAutomatedTransactionHandler.log.log(Level.WARNING, "Unable to automatically start the transaction", e);
			}
		}
	}

	@Override
	public boolean transactionExists(EntityManager entityManager)
	{
		BitronixTransactionManager userTransaction = null;
		try
		{
			userTransaction = (BitronixTransactionManager) BTMAutomatedTransactionHandler.bitronixContext.lookup("java:comp/UserTransaction");
			return userTransaction.getStatus() == 0;
		}
		catch (Exception e)
		{
			BTMAutomatedTransactionHandler.log.log(Level.SEVERE, "BTM Cannot be fetched!");
			return false;
		}

	}

	@Override
	public boolean active()
	{
		return BTMAutomatedTransactionHandler.active;
	}
}
