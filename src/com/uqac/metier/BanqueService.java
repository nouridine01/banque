package com.uqac.metier;

import com.uqac.dao.ClientDao;
import com.uqac.dao.CompteDao;
import com.uqac.dao.UserDao;
import com.uqac.entities.Compte;
import com.uqac.utils.Factory;

public class BanqueService implements IBanqueService {
	private ClientDao clientDao= Factory.getClientDao();
	private UserDao userDao= Factory.getUserDao();
	private CompteDao compteDao = Factory.getCompteDao();
	
	@Override
	public void virement(Long codeFrom, Long codeTo, double montant) {
		// TODO Auto-generated method stub
		Compte cFrom = compteDao.find(codeFrom);
		Compte cTo = compteDao.find(codeTo);
		
		cFrom.setSolde(cFrom.getSolde() - montant);
		cTo.setSolde(cTo.getSolde() + montant);
		
		compteDao.update(cFrom);
		compteDao.update(cTo);
	}

	@Override
	public void retrait(Long codeFrom, double montant) {
		// TODO Auto-generated method stub
		Compte c = compteDao.find(codeFrom);
		c.setSolde(c.getSolde() - montant);
		compteDao.update(c);

	}

	@Override
	public void versement(Long codeTo, double montant) {
		// TODO Auto-generated method stub
		Compte c = compteDao.find(codeTo);
		c.setSolde(c.getSolde() + montant);
		compteDao.update(c);
	}
	
	public void dan(int i) {
		System.out.println("dan "+i);
	}

}
