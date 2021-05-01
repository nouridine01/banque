package com.uqac.dao;

import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
//import javax.transaction.Transactional;

import com.uqac.entities.User;
import com.uqac.utils.TransactionManager;


public class UserDao extends DAO<User>{
	
	public static User userConnected=null;
	
	@Override
	public User find(Long id) {
		
		User u = TransactionManager.getEm().find(User.class, id); 
		
		return u;
	}

	public static User getUserConnected() {
		return userConnected;
	}

	public static void setUserConnected(User userConnected) {
		UserDao.userConnected = userConnected;
	}

	@Override
	public User create(User obj) {
		
		TransactionManager.getEm().persist(obj);
		Query query=TransactionManager.getEm().createQuery("select t from User t order by t.id desc");
		
		return (User) query.getResultList().get(0);
		
		
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		TransactionManager.getEm().merge(obj);	
		return obj;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
		obj =TransactionManager.getEm().find(User.class, obj.getId());
		TransactionManager.getEm().remove(obj);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		Query query=TransactionManager.getEm().createQuery("SELECT s FROM User s"); 
		List<User> list=(List<User>)query.getResultList(); 
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> chercher(String mc) {
		// TODO Auto-generated method stub
		
		Query query=TransactionManager.getEm().createQuery("select t from User t where t.nom like :mc or t.prenom like :mp"); 
		query.setParameter("mc", mc);
		query.setParameter("mp", mc);
		return query.getResultList();
			
	}
	
	public static User connexion(String login,String pwd) {
		
		Query query=TransactionManager.getEm().createQuery("select t from User t where t.login=:log and t.password=:mp "); 
		query.setParameter("log", login);
		query.setParameter("mp", pwd);
		try {
			User u = (User) query.getResultList().get(0);
			return u;
		}catch(IndexOutOfBoundsException e) {
			return null;
		}
		
	}
}
