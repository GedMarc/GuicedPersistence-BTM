package com.jwebmp.guicedpersistence.btm;

/**
 * Sets the transaction isolation for the pooled connections
 * <p>
 * READ_COMMITED default
 */
enum BTMTransactionIsolation
{
	READ_COMMITTED,
	READ_UNCOMMITTED,
	REPEATABLE_READ,
	SERIALIZABLE

}
