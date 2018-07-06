package com.jwebmp.guicedpersistence.btm;

import com.google.inject.matcher.Matchers;
import com.google.inject.persist.Transactional;
import com.jwebmp.guicedinjection.abstractions.GuiceInjectorModule;
import com.jwebmp.guicedinjection.interfaces.GuiceDefaultBinder;

@SuppressWarnings("unused")
public class GuicedPersistenceInterceptionBinding
		extends GuiceDefaultBinder
{

	@Override
	public void onBind(GuiceInjectorModule module)
	{
		module.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), new TransactionHandler());
		module.bindInterceptor(Matchers.any(), Matchers.annotatedWith(com.jwebmp.guicedpersistence.db.annotations.Transactional.class), new InternalTransactionHandler());
	}
}
