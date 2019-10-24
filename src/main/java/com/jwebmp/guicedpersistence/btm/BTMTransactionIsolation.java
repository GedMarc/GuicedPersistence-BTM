package com.guicedee.guicedpersistence.btm;

/**
 * Sets the transaction isolation for the pooled connections
 * <p>
 * READ_COMMITED default
 */
public enum BTMTransactionIsolation
{
	READ_COMMITTED,
	READ_UNCOMMITTED,
	REPEATABLE_READ,
	SERIALIZABLE
}
