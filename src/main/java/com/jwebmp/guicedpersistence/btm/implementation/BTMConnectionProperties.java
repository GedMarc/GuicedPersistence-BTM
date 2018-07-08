package com.jwebmp.guicedpersistence.btm.implementation;

import com.jwebmp.guicedpersistence.db.PropertiesEntityManagerReader;

import java.util.HashMap;
import java.util.Map;

public class BTMConnectionProperties
		implements PropertiesEntityManagerReader
{
	@Override
	public Map<String, String> processProperties()
	{
		Map<String, String> properties = new HashMap<>();

		properties.put("hibernate.current_session_context_class", "jta");
		properties.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
		properties.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.BTMTransactionManagerLookup");
		properties.put("hibernate.jndi.class", "bitronix.tm.jndi.BitronixInitialContextFactory");

		return properties;
	}
}
