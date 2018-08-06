import com.jwebmp.guicedinjection.interfaces.IGuiceDefaultBinder;
import com.jwebmp.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.jwebmp.guicedpersistence.btm.implementation.BTMConnectionProperties;
import com.jwebmp.guicedpersistence.btm.implementation.BTMGuicedPersistenceInterceptionBinding;
import com.jwebmp.guicedpersistence.db.PropertiesEntityManagerReader;
import com.jwebmp.guicedpersistence.services.ITransactionHandler;

module com.jwebmp.guicedpersistence.btm {
	exports com.jwebmp.guicedpersistence.btm.implementation;
	exports com.jwebmp.guicedpersistence.btm;

	requires com.google.common;
	requires com.jwebmp.guicedpersistence;
	requires btm;
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

	provides IGuiceDefaultBinder with BTMGuicedPersistenceInterceptionBinding;
	provides PropertiesEntityManagerReader with BTMConnectionProperties;
	provides ITransactionHandler with BTMAutomatedTransactionHandler;
}
