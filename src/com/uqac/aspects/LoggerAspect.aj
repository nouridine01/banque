package com.uqac.aspects;

import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import javax.persistence.EntityTransaction;

import java.util.logging.Logger;
import com.uqac.utils.TransactionManager; 

public aspect LoggerAspect {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	FileHandler fh;  
    
    pointcut logging(): call(* com.uqac.metier.IBanqueService+.*(..));
	Object around() : logging(){ 
		  
		try {
			
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("C:/tmp/MyLogFile.log");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
	       
			Object ret= proceed(); 
			
			if(thisJoinPoint.getSignature().getName().equals("virement")) {
	        	Long codeFrom = (Long)thisJoinPoint.getArgs()[0];
				Long codeTo = (Long)thisJoinPoint.getArgs()[1];
				double montant = (double)thisJoinPoint.getArgs()[2];
				// the following statement is used to log any messages  
		        log.info("operation de "+thisJoinPoint.getSignature().getName()+" de "+montant+" effectué avec succès du compte n°"+codeFrom+" vers le compte n°"+codeTo); 
	        }else {
	        	Long code = (Long)thisJoinPoint.getArgs()[0];
				double montant =(double) thisJoinPoint.getArgs()[1];
				log.info("operation de "+thisJoinPoint.getSignature().getName()+" de "+montant+" effectué avec succès du compte n°"+code); 
	        }

			return ret;
		}catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		
		return null;
	}
	
	
}
