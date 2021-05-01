package com.uqac.dao;

import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
//import javax.transaction.Transactional;

import com.uqac.entities.Compte;
import com.uqac.utils.TransactionManager;


public class CompteDao extends DAO<Compte>{
	
	
	
	@Override
	public Compte find(Long id) {
		
		Compte compte = TransactionManager.getEm().find(Compte.class, id); 
		
		return compte;
	}

	
	@Override
	public Compte create(Compte obj) {
		
		TransactionManager.getEm().persist(obj);
		Query query=TransactionManager.getEm().createQuery("select t from Compte t order by t.code desc");
		
		return (Compte) query.getResultList().get(0);
		
	}

	@Override
	public Compte update(Compte obj) {
		// TODO Auto-generated method stub
		
		TransactionManager.getEm().merge(obj);
		
		return obj;
	}

	@Override
	public void delete(Compte obj) {
		// TODO Auto-generated method stub
		
		obj =TransactionManager.getEm().find(Compte.class, obj.getCode());
		TransactionManager.getEm().remove(obj);
		
	}

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		
		Query query=TransactionManager.getEm().createQuery("SELECT s FROM Compte s"); 
		@SuppressWarnings("unchecked")
		List<Compte> list=(List<Compte>)query.getResultList(); 
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> chercher(String mc) {
		// TODO Auto-generated method stub
		
		Query query=TransactionManager.getEm().createQuery("select t from Compte t where t.solde like :mc or t.date_creation like :mp"); 
		query.setParameter("mc", mc);
		query.setParameter("mp", mc);
		return query.getResultList();
			
	}
	
	
}
