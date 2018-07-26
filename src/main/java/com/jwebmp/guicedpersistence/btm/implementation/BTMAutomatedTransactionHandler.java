package com.jwebmp.guicedpersistence.btm.implementation;

import bitronix.tm.jndi.BitronixContext;
import com.jwebmp.guicedpersistence.services.ITransactionHandler;
import com.jwebmp.logger.LogFactory;

import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BTMAutomatedTransactionHandler
		implements ITransactionHandler
{
	private static final Logger log = LogFactory.getLog("BTMAutomatedTransactionHandler");
	private static boolean Active = false;
	private BitronixContext bc = new BitronixContext();
	;
	private boolean transactionExistedDuringBegin = false;

	@Override
	public void beginTransacation(boolean createNew, EntityManager entityManager)
	{
		BitronixContext bc = new BitronixContext();
		UserTransaction userTransaction;
		try
		{
			transactionExistedDuringBegin = transactionExists(entityManager);
			userTransaction = (UserTransaction) bc.lookup("java:comp/UserTransaction");
			if (createNew || !transactionExists(entityManager) || userTransaction.getStatus() != Status.STATUS_ACTIVE)
			{
				try
				{
					userTransaction = (UserTransaction) bc.lookup("java:comp/UserTransaction");
					userTransaction.begin();
				}
				catch (Exception e)
				{
					log.log(Level.WARNING, "Unable to automatically start the transaction", e);
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "Unable to automatically start the transaction", e);
			return;
		}
	}

	@Override
	public void commitTransacation(boolean createNew, EntityManager entityManager)
	{
		if (createNew || !transactionExists(entityManager))
		{
			try
			{
				UserTransaction userTransaction = (UserTransaction) bc.lookup("java:comp/UserTransaction");
				if (userTransaction.getStatus() == Status.STATUS_ACTIVE && !transactionExistedDuringBegin)
				{
					userTransaction.commit();
				}
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "Unable to automatically start the transaction", e);
			}
		}
	}

	@Override
	public boolean transactionExists(EntityManager entityManager)
	{

		UserTransaction userTransaction = null;
		try
		{
			userTransaction = (UserTransaction) bc.lookup("java:comp/UserTransaction");
			return userTransaction.getStatus() == Status.STATUS_ACTIVE;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "BTM Cannot be fetched!");
			return false;
		}

	}

	@Override
	public boolean active()
	{
		return Active;
	}

	public static boolean isActive()
	{
		return Active;
	}

	public static void setActive(boolean active)
	{
		Active = active;
	}
}
