module com.guicedee.guicedpersistence.btm {
	exports com.guicedee.guicedpersistence.btm.implementation;
	exports com.guicedee.guicedpersistence.btm;

	requires transitive com.guicedee.guicedpersistence;
	requires transitive tm.bitronix.btm;

	requires transitive java.sql;
	requires static java.naming;

	requires transitive com.guicedee.guicedpersistence.readers.hibernateproperties;

	provides com.guicedee.guicedpersistence.services.IPropertiesEntityManagerReader with com.guicedee.guicedpersistence.btm.implementation.BTMConnectionProperties;
	provides com.guicedee.guicedpersistence.services.ITransactionHandler with com.guicedee.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
	provides com.guicedee.guicedinjection.interfaces.IGuicePreDestroy with com.guicedee.guicedpersistence.btm.implementation.BTMDestroyer;

}
