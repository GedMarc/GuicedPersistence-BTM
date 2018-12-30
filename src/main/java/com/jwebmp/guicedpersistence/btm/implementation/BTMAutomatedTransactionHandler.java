package com.jwebmp.guicedpersistence.btm.implementation;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.jndi.BitronixContext;
import com.google.common.base.Strings;
import com.jwebmp.guicedpersistence.services.ITransactionHandler;
import com.jwebmp.logger.LogFactory;
import com.oracle.jaxb21.PersistenceUnit;

import javax.persistence.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BTMAutomatedTransactionHandler
		implements ITransactionHandler<BTMAutomatedTransactionHandler>
{
	/**
	 * Field log
	 */
	private static final Logger log = LogFactory.getLog("BTMAutomatedTransactionHandler");
	/**
	 * Field bitronixContext
	 */
	private static final BitronixContext bc = new BitronixContext();

	private static final String UserTransactionReference = "java:comp/UserTransaction";

	/**
	 * Field active
	 */
	private static boolean active = true;

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
	public void beginTransacation(boolean createNew, EntityManager entityManager, PersistenceUnit persistenceUnit)
	{
		try
		{
			BitronixTransactionManager userTransaction = (BitronixTransactionManager) bc.lookup(UserTransactionReference);
			userTransaction.begin();
		}
		catch (Exception e)
		{
			BTMAutomatedTransactionHandler.log.log(Level.WARNING, "Unable to being a transaction for BTM", e);
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
	public void commitTransacation(boolean createNew, EntityManager entityManager, PersistenceUnit persistenceUnit)
	{
		try
		{
			BitronixTransactionManager userTransaction = (BitronixTransactionManager) bc.lookup(UserTransactionReference);
			userTransaction.commit();
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
	public void rollbackTransacation(boolean createNew, EntityManager entityManager, PersistenceUnit persistenceUnit)
	{
		try
		{
			BitronixTransactionManager userTransaction = (BitronixTransactionManager) bc.lookup(UserTransactionReference);
			userTransaction.rollback();
		}
		catch (Exception e)
		{
			BTMAutomatedTransactionHandler.log.log(Level.WARNING, "Unable to rollback start the transaction", e);
		}
	}

	@Override
	public boolean transactionExists(EntityManager entityManager, PersistenceUnit persistenceUnit)
	{
		try
		{
			BitronixTransactionManager userTransaction = (BitronixTransactionManager) bc.lookup(UserTransactionReference);
			return userTransaction.getStatus() == 0;
		}
		catch (Exception e)
		{
			BTMAutomatedTransactionHandler.log.log(Level.SEVERE, "BTM Cannot be fetched!", e);
			return false;
		}
	}

	@Override
	public boolean active(PersistenceUnit persistenceUnit)
	{
		if (active)
		{
			if(!Strings.isNullOrEmpty(persistenceUnit.getTransactionType()
			                                         .value())
			   && !"RESOURCE_LOCAL".equals(persistenceUnit.getTransactionType()
			                                              .value()))
				return true;
			return !Strings.isNullOrEmpty(persistenceUnit.getJtaDataSource());
		}
		return false;
	}

}
