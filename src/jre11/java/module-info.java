import com.guicedee.guicedinjection.interfaces.IGuicePreDestroy;
import com.guicedee.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions;
import com.guicedee.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.guicedee.guicedpersistence.btm.implementation.BTMConnectionProperties;
import com.guicedee.guicedpersistence.btm.implementation.BTMDestroyer;
import com.guicedee.guicedpersistence.btm.implementation.BTMModuleExclusions;
import com.guicedee.guicedpersistence.services.IPropertiesEntityManagerReader;
import com.guicedee.guicedpersistence.services.ITransactionHandler;

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

	provides IGuiceScanModuleExclusions with BTMModuleExclusions;
	provides IGuiceScanJarExclusions with BTMModuleExclusions;

	provides IPropertiesEntityManagerReader with BTMConnectionProperties;
	provides ITransactionHandler with BTMAutomatedTransactionHandler;
	provides IGuicePreDestroy with BTMDestroyer;

}
