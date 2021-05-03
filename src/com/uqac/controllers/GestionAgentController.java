package com.uqac.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.uqac.App;
import com.uqac.dao.UserDao;
import com.uqac.entities.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;

import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
//import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableView;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class GestionAgentController implements Initializable{
	
		private String view;
		private String titre;
		private static UserDao dao = new UserDao();
		private static  User userSelected=new User();
		@FXML
		private MenuItem GestionClient;
		@FXML
		private MenuItem logout;
		@FXML
		private TableColumn<User,String> LoginC;
		@FXML
		private TableColumn<User,String> PasswordC;
		@FXML
		private TableColumn<User,Date> DateNaissanceC;
		@FXML
		private TableColumn<User,String> AdresseC;
		@FXML
		private TableColumn<User,String> NumeroTelephoneC;
		@FXML
		private TableColumn<User,String> RoleC;
		@FXML
		private TableView<User> TableAgent;
		@FXML
		private TextField NumeroTelephone;
		@FXML
		private DatePicker DateNaissance;
		@FXML
		private TextField Adresse;
		@FXML
		private TextField Login;
		@FXML
		private PasswordField Password;
		@FXML
		private ComboBox<String> Role;
		@FXML
		private Button NouveauAgent;
		@FXML
		private Button ModifierAgent;
		@FXML
		private Button SupprimerAgent;
		@FXML
		private TextField SearchTxt;
		@FXML
		private Button SearchBtn;
	

		// Event Listener on MenuItem[#listerUser].onAction
		@FXML
		public void GestionClient(ActionEvent event) {
			// TODO Autogenerated
			App app = new App();
			view="GestionClient";
			titre="GESTION CLIENT";
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
		public void NouveauAgent(ActionEvent event) {
			// TODO Autogenerated
			User user = recupererChamp();
			dao.create(user);
			viderChamp();
			etatButtons(true);
			initTable(1,"");
			userSelected=null;
			
		}
		
		@FXML
		public void ModifierAgent(ActionEvent event) {
			// TODO Autogenerated
			User user = recupererChamp();
			user.setId(userSelected.getId());
			dao.update(user);
			viderChamp();
			etatButtons(true);
			initTable(1,"");
			userSelected=null;
		}
		
		@FXML
		public void SupprimerAgent(ActionEvent event) {
			// TODO Autogenerated
			User user = new User();
			user.setId(userSelected.getId());
			dao.delete(user);
			viderChamp();
			etatButtons(true);
			initTable(1,"");
			userSelected=null;
		}
		
		@FXML
		public void SearchBtn(ActionEvent event) {
			// TODO Autogenerated
			String mcl= SearchTxt.getText();
			if(!mcl.isEmpty()) {			
				initTable(2,mcl);
			}else {
				initTable(1,"");
			}
			
		}
		@FXML
		public void init(ActionEvent event) {
			viderChamp();
			userSelected=null;
			etatButtons(true);
		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			initTable(1,"");
			initRole();
			etatButtons(true);
			DateNaissance.setValue(LocalDate.now());
			TableAgent.getSelectionModel().selectedItemProperty().addListener(
		            (observable, oldValue, newValue) -> {
		           userSelected =(User) TableAgent.getSelectionModel().getSelectedItem();
		       
		           if(userSelected!=null) {
		        	   etatButtons(false);
		        	   remplirChamp();
		           }
		           
		            	
		      });
			
			
		}
		
		private void initTable(int typeListe,String mcl) {
			TableAgent.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			List<User> users ;
			ObservableList<User> liste;
			if(typeListe==1) {
				users = dao.findAll();
				liste = FXCollections.observableArrayList(users);
			}else {
				users = dao.chercher(mcl);
				liste = FXCollections.observableArrayList(users);
			}
			TableAgent.setItems(liste);
			LoginC.setCellValueFactory(new PropertyValueFactory<>("login"));
			PasswordC.setCellValueFactory(new PropertyValueFactory<>("password"));
			DateNaissanceC.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
			AdresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			NumeroTelephoneC.setCellValueFactory(new PropertyValueFactory<>("telephone"));
			RoleC.setCellValueFactory(new PropertyValueFactory<>("role"));
		}
		
		public void initRole() {
			ObservableList<String> roles = FXCollections.observableArrayList();
			roles.addAll("AGENT");
			Role.setItems(roles);
		}
		
		private void etatButtons(Boolean b) {
			ModifierAgent.setDisable(b);
			SupprimerAgent.setDisable(b);
		}
		
		private void remplirChamp() {

			Login.textProperty().set(userSelected.getLogin());
			Password.textProperty().set(userSelected.getPassword());
			NumeroTelephone.textProperty().set(userSelected.getTelephone());
			DateNaissance.setValue(Instant.ofEpochMilli(userSelected.getDate_naiss().getTime())
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
			Adresse.textProperty().set(userSelected.getAdresse());
			Role.setValue(userSelected.getRole());
			
		}
		
		private void viderChamp() {
			Login.textProperty().set("");
			Password.textProperty().set("");
			DateNaissance.setValue(LocalDate.now());
			Adresse.textProperty().set("");
			NumeroTelephone.textProperty().set("");
			
		}
		
		private User recupererChamp() {
			User user = new User();

			user.setLogin(Login.getText());
			user.setPassword(Password.getText());
			user.setDate_naiss(java.sql.Date.valueOf(DateNaissance.getValue()));
			user.setAdresse(Adresse.getText());
			user.setTelephone(NumeroTelephone.getText());
			user.setRole(Role.getValue().toString());
			
			return user;
		}
	}

