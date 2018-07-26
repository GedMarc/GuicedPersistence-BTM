package com.jwebmp.guicedpersistence.btm.implementation;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.jndi.BitronixContext;
import com.google.inject.persist.Transactional;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.naming.NamingException;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class TransactionHandler
		implements MethodInterceptor
{
	private static final Logger log = Logger.getLogger("TransactionHandler");

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		BitronixTransactionManager ut = null;
		try
		{
			ut = getUserTransaction();
		}
		catch (Exception T)
		{
			log.log(Level.SEVERE, "Unable to get User Transaction", T);
			return invocation.proceed();
		}

		Object returnable = null;
		boolean transactionWasStartedOutside = false;
		if (ut.getStatus() != 6)
		{
			ut.begin();
		}
		else
		{
			transactionWasStartedOutside = true;
		}

		try
		{
			returnable = invocation.proceed();
			if (!transactionWasStartedOutside)
			{
				ut.commit();
			}
		}
		catch (IllegalStateException ise)
		{
			log.log(Level.FINEST, "Nothing to commit in transaction?", ise);
		}
		catch (Throwable T)
		{
			Transactional t = invocation.getMethod()
			                            .getAnnotation(Transactional.class);
			for (Class<? extends Exception> aClass : t.rollbackOn())
			{
				if (aClass.isAssignableFrom(T.getClass()))
				{
					log.log(Level.FINE, "Exception In Commit Rolled Back from class [" + T.getClass() + "]: ", T);
					ut.rollback();
				}
			}
			log.log(Level.SEVERE, "Exception In Commit : " + T.getMessage(), T);
			throw T;
		}
		return returnable;
	}

	/**
	 * Returns the current user transaction
	 *
	 * @return A UserTransaction of type BitronixTransactionManager as JDK9 no longer has UserTransaction
	 *
	 * @throws javax.naming.NamingException
	 * 		If java:comp/UserTransaction has not been bound
	 */
	@NotNull
	public static BitronixTransactionManager getUserTransaction() throws NamingException
	{
		BitronixContext ic = new BitronixContext();
		return (BitronixTransactionManager) ic.lookup("java:comp/UserTransaction");
	}
}
