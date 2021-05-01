package com.uqac.utils;


import javax.persistence.EntityTransaction;


public interface ITransactionManager {
	public EntityTransaction getEntityTransaction();
	public void commit(EntityTransaction t);
	public void rollback(EntityTransaction t);

}
