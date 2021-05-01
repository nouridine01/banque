package com.uqac.aspects;

import com.uqac.entities.Compte;

public aspect PatchAspect {
	pointcut patch(double mt) : call(* metier..Compte.retirer(..)) && args(mt);
	
	void around(double mt) : patch(mt) {
		Compte cp = (Compte) thisJoinPoint.getTarget();
		if(mt<cp.getSolde()){
			proceed(mt);
		}else{
			throw new RuntimeException("solde insuffisant");
		}
	}

}
