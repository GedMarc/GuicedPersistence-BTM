import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;
import com.jwebmp.guicedinjection.interfaces.IGuicePreDestroy;
import com.jwebmp.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.jwebmp.guicedpersistence.btm.implementation.BTMConnectionProperties;
import com.jwebmp.guicedpersistence.btm.implementation.BTMDestroyer;
import com.jwebmp.guicedpersistence.btm.implementation.BTMPostStartup;
import com.jwebmp.guicedpersistence.services.ITransactionHandler;
import com.jwebmp.guicedpersistence.services.PropertiesEntityManagerReader;

module com.jwebmp.guicedpersistence.btm {
	exports com.jwebmp.guicedpersistence.btm.implementation;
	exports com.jwebmp.guicedpersistence.btm;

	requires transitive com.jwebmp.guicedpersistence;

	requires transitive btm;
	requires transitive com.jwebmp.guicedpersistence.readers.hibernateproperties;

	provides PropertiesEntityManagerReader with BTMConnectionProperties;
	provides ITransactionHandler with BTMAutomatedTransactionHandler;
	provides IGuicePostStartup with BTMPostStartup;
	provides IGuicePreDestroy with BTMDestroyer;

}
