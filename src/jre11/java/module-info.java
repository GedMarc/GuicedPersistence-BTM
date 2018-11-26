import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;
import com.jwebmp.guicedinjection.interfaces.IGuicePreDestroy;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;
import com.jwebmp.guicedpersistence.btm.implementation.*;
import com.jwebmp.guicedpersistence.services.ITransactionHandler;
import com.jwebmp.guicedpersistence.services.PropertiesEntityManagerReader;

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

	provides IGuiceScanModuleExclusions with BTMModuleExclusions;
	provides IGuiceScanJarExclusions with BTMModuleExclusions;

	provides PropertiesEntityManagerReader with BTMConnectionProperties;
	provides ITransactionHandler with BTMAutomatedTransactionHandler;
	provides IGuicePostStartup with BTMPostStartup;
	provides IGuicePreDestroy with BTMDestroyer;

}
