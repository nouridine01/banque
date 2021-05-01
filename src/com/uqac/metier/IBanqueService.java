package com.uqac.metier;

public interface IBanqueService {
	public void virement(Long codeFrom,Long codeTo,double montant);
	public void retrait(Long codeFrom,double montant);
	public void versement(Long codeTo,double montant);
}
