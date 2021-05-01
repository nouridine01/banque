package com.uqac.utils;

import com.uqac.dao.ClientDao;
import com.uqac.dao.CompteDao;
import com.uqac.dao.UserDao;

public class Factory {
	private static ITransactionManager transactionManager = null;
	private static ClientDao clientDao = null;
	private static CompteDao compteDao = null;
	private static UserDao userDao = null;
	
	private Factory() {
		
	}
	
	public static ITransactionManager getTransactionManager(){
		if(transactionManager == null) transactionManager = new TransactionManager();
		return transactionManager;
	}
	
	public static ClientDao getClientDao(){
		if(clientDao == null) clientDao = new ClientDao();
		return clientDao;
	}
	
	public static CompteDao getCompteDao(){
		if(compteDao == null) compteDao = new CompteDao();
		return compteDao;
	}
	
	public static UserDao getUserDao(){
		if(userDao == null) userDao = new UserDao();
		return userDao;
	}
	
	

}
