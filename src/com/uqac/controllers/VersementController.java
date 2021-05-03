package com.uqac.controllers;

//import java.awt.TextField;

import com.uqac.App;
import com.uqac.dao.CompteDao;
import com.uqac.metier.BanqueService;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class VersementController {
	String view;
	String titre;
	@FXML
	private TextField Numerocompte;
	@FXML
	private TextField montant;
	@FXML
	private Button EffectuerVersement;
	@FXML
	private MenuItem Virement;
	@FXML
	private MenuItem Retrait;
	@FXML
	private MenuItem GestionClient;
	@FXML
	private MenuItem GestionCompte;
	@FXML
	private MenuItem logout;

	
	@FXML
	public void GestionClient(ActionEvent event) {
		// TODO Autogenerated
		App app = new App();
		view="GestionClient";
		titre="GESTION CLIENT";
		app.changeView(view, titre);
	}
		@FXML
		public void Virement(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="Virement";
			titre="VIREMENT";
			app.changeView(view, titre);
		}
		
		@FXML
		public void Retrait(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="Retrait";
			titre="RETRAIT";
			app.changeView(view, titre);
		}
		
		@FXML
		public void GestionCompte(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="Compte";
			titre="GESTION COMPTE";
			app.changeView(view, titre);
		}
		
		@FXML
		public void logout(ActionEvent event) {
			App app = new App();
			view="Login";
			titre="LOGIN";
			app.changeView(view, titre);
		}
		
		@FXML
		public void EffectuerVersement(ActionEvent event) {
			
			long NumC = Long.parseLong(Numerocompte.getText());
			long Mont = Long.parseLong(montant.getText());
			CompteDao dao = new CompteDao();
			BanqueService Bank = new BanqueService();
			if (dao.find(NumC) != null)
			{
				Bank.versement(NumC,Mont);	 
				Alert info = new Alert(AlertType.INFORMATION);
			    info.setTitle("Versement");
			    info.setHeaderText("Versement effectue");
			    info.showAndWait();
			    Numerocompte.setText("");
			    montant.setText("");
			}
			else
			{
				Alert info = new Alert(AlertType.ERROR);
		           info.setTitle("Saisie des informations");
		           info.setHeaderText("veuillez saisir des Informations valide");
		           info.showAndWait();
			}
//			App app = new App();
//			view="GestionClient";
//			titre="GESTION CLIENTS";
//			app.changeView(view, titre);
		}
		
	
}
