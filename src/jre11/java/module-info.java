module com.jwebmp.guicedpersistence.btm {
	exports com.jwebmp.guicedpersistence.btm.implementation;
	exports com.jwebmp.guicedpersistence.btm;

	requires com.google.common;
	requires transitive com.jwebmp.guicedpersistence;
	requires tm.bitronix.btm;

	requires java.sql;
	requires com.jwebmp.logmaster;
	requires java.persistence;

	requires java.transaction;

	requires com.jwebmp.guicedinjection;
	requires com.google.guice.extensions.persist;
	requires com.google.guice;
	requires aopalliance;
	requires java.validation;
	requires java.naming;

	requires com.jwebmp.guicedpersistence.readers.hibernateproperties;

	provides com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.jwebmp.guicedpersistence.btm.implementation.BTMModuleExclusions;
	provides com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions with com.jwebmp.guicedpersistence.btm.implementation.BTMModuleExclusions;

	provides com.jwebmp.guicedpersistence.services.IPropertiesEntityManagerReader with com.jwebmp.guicedpersistence.btm.implementation.BTMConnectionProperties;
	provides com.jwebmp.guicedpersistence.services.ITransactionHandler with com.jwebmp.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
	provides com.jwebmp.guicedinjection.interfaces.IGuicePostStartup with com.jwebmp.guicedpersistence.btm.implementation.BTMPostStartup;
	provides com.jwebmp.guicedinjection.interfaces.IGuicePreDestroy with com.jwebmp.guicedpersistence.btm.implementation.BTMDestroyer;

}
