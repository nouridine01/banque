package com.uqac.aspects;





import com.uqac.dao.ClientDao;
import com.uqac.dao.CompteDao;
import com.uqac.entities.Compte;
import com.uqac.utils.Factory;

public aspect SecuriteAspect {
	/*private String login;
	private String pwd;

	pointcut securite(): call(*..App.new(..));
	
	Object around() : securite() {
		if(login == null){
			Scanner sc = new Scanner(System.in);
			System.out.println("donner le login : ");
			String login = sc.next();
			
			System.out.println("donner le password : ");
			String pwd = sc.next();
			
			if(login.equals("root") && pwd.equalsIgnoreCase("root")){
				this.login=login;
				this.pwd=pwd;
				return proceed();
			}else{
				throw new RuntimeException("Accés réfusé");
			}
			
		}
		return null;
	}*/
	private ClientDao clientDao= Factory.getClientDao();
	
	private CompteDao compteDao = Factory.getCompteDao();
	pointcut securite(): call (* virement(..)) || call(* retrait(..)) ;
	void around() : securite(){
		String methodeName = thisJoinPoint.getSignature().getName();
		
		String montantOperation ="";
		
		
		if(methodeName.matches("virement")) {//Long codeFrom, Long codeTo, double montant
			// Récupération des paramètres des méthodes de Banque Service
			Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
			//Récupération du compte émetteur du virement
			Compte cFrom = compteDao.find(codeFrom);
			
			double montant = (double)thisJoinPoint.getArgs()[2];
			
			
			if(cFrom.getSolde()>=montant) {
				proceed();
			}else {
				throw new RuntimeException("Solde compte émetteur insuffisant");
			}
			//vérification du monrant sur le compte émetteur
			
		}else if(methodeName.matches("retrait")) {//Long codeFrom, double montant
			// Récupération des paramètres des méthodes de Banque Service
			Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
			Compte cFrom = compteDao.find(codeFrom);
			double montant = (double)thisJoinPoint.getArgs()[1];
			
			if(cFrom.getSolde()>=montant) {
				proceed();
			}else {
				throw new RuntimeException("Solde compte émetteur insuffisant");
			}
			
			
			
		}
	}
	
	
}
