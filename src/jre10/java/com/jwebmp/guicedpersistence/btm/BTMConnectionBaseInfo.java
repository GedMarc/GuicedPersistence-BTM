package com.jwebmp.guicedpersistence.btm;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import com.jwebmp.guicedpersistence.db.ConnectionBaseInfo;

import java.io.Serializable;

/**
 * This class is a basic container (mirror) for the database jtm builder string.
 * Exists to specify the default properties for connections that a jtm should implement should btm be switched for a different
 * implementation
 */
public class BTMConnectionBaseInfo
		extends ConnectionBaseInfo
		implements Serializable, Cloneable
{
	private static final long serialVersionUID = 1L;

	public BTMConnectionBaseInfo()
	{
		setServerInstanceNameProperty("JDK10 No JTA");
	}

	/**
	 * Configures this handler as either an XA or Non-XA Resource
	 *
	 * @param xa
	 */
	public BTMConnectionBaseInfo(boolean xa)
	{
		setXa(xa);
	}

	/**
	 * Returns the BTM Pooling Data Source Configured
	 *
	 * @return
	 */
	@Override
	public PoolingDataSource toPooledDatasource()
	{
		throw new java.lang.UnsupportedOperationException("Haven't quite figured out JTA for JDK 10 just yet, without a compiler argument for patch-module");
	}

}
