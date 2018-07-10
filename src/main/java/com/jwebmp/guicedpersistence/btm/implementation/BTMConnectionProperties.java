package com.jwebmp.guicedpersistence.btm.implementation;

import com.jwebmp.guicedpersistence.db.PropertiesEntityManagerReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BTMConnectionProperties
		implements PropertiesEntityManagerReader
{
	@Override
	public Map<String, String> processProperties(Properties properties)
	{
		Map<String, String> props = new HashMap<>();

		props.put("hibernate.current_session_context_class", "jta");
		props.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
		props.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.BTMTransactionManagerLookup");
		props.put("hibernate.jndi.class", "bitronix.tm.jndi.BitronixInitialContextFactory");

		return props;
	}
}
