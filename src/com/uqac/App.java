package com.uqac;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.uqac.dao.Singleton;
import com.uqac.dao.UserDao;
import com.uqac.entities.User;
import com.uqac.metier.BanqueService;
import com.uqac.utils.Factory;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
	private static Stage primary;

	public static Stage getPrimary() {
		return primary;
	}

	public static void setPrimary(Stage primary) {
		App.primary = primary;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton.getEntityManagetFactory();
		UserDao d=Factory.getUserDao();
		User u= new User();
		u.setAdresse("bla");
		u.setLogin("noor");
		u.setPassword("passer");
		//d.create(u);
		BanqueService bs = new BanqueService();
		//bs.dan(5);
		//System.out.println(u.getId());
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primary=primaryStage;
		primary.setTitle("Login");
		Parent root =  new FXMLLoader(getClass().getResource("views/Login.fxml")).load();
		//Parent root =  new FXMLLoader(getClass().getResource("views/AccueilManager.fxml")).load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primary.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		primaryStage.centerOnScreen();
		
	}
	
	public static void exit() {
		primary.close();
	}

	
	public  void  changeView(String view,String titre) {
		Parent root;
		try {
			root = new FXMLLoader(getClass().getResource("views/"+view+".fxml")).load();
			Scene scene = new Scene(root);
			Stage s = App.getPrimary();
			s.hide();
			s.setTitle(titre);
			//s.setResizable(false);
			s.setScene(scene);
			s.show();
			s.centerOnScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
