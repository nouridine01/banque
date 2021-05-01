package com.uqac.dao;

import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
//import javax.transaction.Transactional;

import com.uqac.entities.Client;
//import com.uqac.entities.User;
import com.uqac.utils.TransactionManager;


public class ClientDao extends DAO<Client>{
	
	
	@Override
	public Client find(Long id) {
		
		Client client = TransactionManager.getEm().find(Client.class, id); 
		
		return client;
	}

	
	@Override
	public Client create(Client obj) {
		
		TransactionManager.getEm().persist(obj);
		Query query=TransactionManager.getEm().createQuery("select t from Client t order by t.id desc");
		
		return (Client) query.getResultList().get(0);
		
	}

	@Override
	public Client update(Client obj) {
		// TODO Auto-generated method stub
		
		TransactionManager.getEm().merge(obj);
		
		return obj;
	}

	@Override
	public void delete(Client obj) {
		// TODO Auto-generated method stub
		
		obj =TransactionManager.getEm().find(Client.class, obj.getId());
		TransactionManager.getEm().remove(obj);
		
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		
		Query query=TransactionManager.getEm().createQuery("SELECT s FROM Client s"); 
		@SuppressWarnings("unchecked")
		List<Client> list=(List<Client>)query.getResultList(); 
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> chercher(String mc) {
		// TODO Auto-generated method stub
		
		Query query=TransactionManager.getEm().createQuery("select t from Client t where t.nom like :mc or t.prenom like :mp"); 
		query.setParameter("mc", mc);
		query.setParameter("mp", mc);
		return query.getResultList();
			
	}
	
	
}
