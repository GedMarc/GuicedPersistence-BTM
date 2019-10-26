module com.guicedee.guicedpersistence.btm {
	exports com.guicedee.guicedpersistence.btm.implementation;
	exports com.guicedee.guicedpersistence.btm;

	requires com.google.common;
	requires transitive com.guicedee.guicedpersistence;
	requires tm.bitronix.btm;

	requires java.sql;
	requires com.guicedee.logmaster;
	requires java.persistence;

	requires java.transaction;

	requires com.guicedee.guicedinjection;
	requires com.google.guice.extensions.persist;
	requires com.google.guice;
	requires aopalliance;
	requires java.validation;
	requires java.naming;

	requires com.guicedee.guicedpersistence.readers.hibernateproperties;

	provides com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions with com.guicedee.guicedpersistence.btm.implementation.BTMModuleExclusions;
	provides com.guicedee.guicedinjection.interfaces.IGuiceScanJarExclusions with com.guicedee.guicedpersistence.btm.implementation.BTMModuleExclusions;

	provides com.guicedee.guicedpersistence.services.IPropertiesEntityManagerReader with com.guicedee.guicedpersistence.btm.implementation.BTMConnectionProperties;
	provides com.guicedee.guicedpersistence.services.ITransactionHandler with com.guicedee.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
	provides com.guicedee.guicedinjection.interfaces.IGuicePreDestroy with com.guicedee.guicedpersistence.btm.implementation.BTMDestroyer;

}
