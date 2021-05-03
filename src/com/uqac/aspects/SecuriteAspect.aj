package com.uqac.aspects;





import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.uqac.dao.ClientDao;
import com.uqac.dao.CompteDao;
import com.uqac.entities.Compte;
import com.uqac.utils.Factory;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public aspect SecuriteAspect {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	FileHandler fh;  
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
	pointcut securite(): call (* com.uqac.metier.IBanqueService+.virement(..)) || call (* com.uqac.metier.IBanqueService+.retrait(..)) ;
	
	void around() : securite(){
		try {
			fh = new FileHandler("C:/tmp/MyLogFile.log");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
	        
	        String methodeName = thisJoinPoint.getSignature().getName();
			
			String montantOperation ="";
			
			
			if(methodeName.matches("virement")) {//Long codeFrom, Long codeTo, double montant
				// Récupération des paramètres des méthodes de Banque Service
				Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
				//Récupération du compte émetteur du virement
				Compte cFrom = compteDao.find(codeFrom);
				Long codeTo = (Long)thisJoinPoint.getArgs()[1];
				double montant = (double)thisJoinPoint.getArgs()[2];
				
				
				if(cFrom.getSolde()>=montant) {
					proceed();
					Alert info = new Alert(AlertType.INFORMATION);
				    info.setTitle("Virement");
				    info.setHeaderText("Virement effectue");
				    info.showAndWait();
				}else {
					log.info("operation de "+thisJoinPoint.getSignature().getName()+" de "+montant+"  du compte n°"+codeFrom+" vers le compte n°"+codeTo+" non effectué car le Solde du compte émetteur est insuffisant");
					Alert info = new Alert(AlertType.WARNING);
				    info.setTitle("Error Versement");
				    info.setHeaderText("le Solde du compte émetteur est insuffisant");
				    info.showAndWait();
				}
				//vérification du monrant sur le compte émetteur
				
			}else if(methodeName.matches("retrait")) {//Long codeFrom, double montant
				// Récupération des paramètres des méthodes de Banque Service
				Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
				Compte cFrom = compteDao.find(codeFrom);
				double montant = (double)thisJoinPoint.getArgs()[1];
				
				if(cFrom.getSolde()>=montant) {
					proceed();
					
				    Alert info = new Alert(AlertType.INFORMATION);
				    info.setTitle("Retrait");
				    info.setHeaderText("Retrait effectue");
				    info.showAndWait();
				}else {
					log.info("operation de "+thisJoinPoint.getSignature().getName()+" de "+montant+" sur le compte n°"+codeFrom+" non effectué car le Solde du compte est insuffisant"); 
					Alert info = new Alert(AlertType.WARNING);
				    info.setTitle("Error Retrait");
				    info.setHeaderText("le Solde du compte est insuffisant");
				    info.showAndWait();
				    
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
}
