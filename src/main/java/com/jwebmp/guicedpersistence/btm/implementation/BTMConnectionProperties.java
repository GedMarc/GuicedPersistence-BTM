package com.jwebmp.guicedpersistence.btm.implementation;

import com.google.common.base.Strings;
import com.jwebmp.guicedpersistence.services.PropertiesEntityManagerReader;
import com.oracle.jaxb21.PersistenceUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class BTMConnectionProperties
		implements PropertiesEntityManagerReader
{
	@Override
	public Map<String, String> processProperties(PersistenceUnit persistenceUnit, Properties properties)
	{
		if (persistenceUnit.getTransactionType() == null ||
		    "RESOURCE_LOCAL".equals(persistenceUnit.getTransactionType().toString()) || Strings.isNullOrEmpty(persistenceUnit.getJtaDataSource()))
		{
			Logger.getLogger("BTMConnectionProperties")
			      .warning("Persistence Unit : " +
			               persistenceUnit.getName() +
			               " is not a JTA resource and may skip BTM Configuration. Consider including C3P0 for these connections.");
		}
		Map<String, String> props = new HashMap<>();

		if (!Strings.isNullOrEmpty(persistenceUnit.getJtaDataSource()))
		{
			props.put("hibernate.connection.datasource", persistenceUnit.getJtaDataSource());
		}

		props.put("hibernate.current_session_context_class", "jta");
		props.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
		props.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.BTMTransactionManagerLookup");
		props.put("hibernate.jndi.class", "bitronix.tm.jndi.BitronixInitialContextFactory");

		return props;
	}
}
