package com.github.brer00.adventura.ui;


import com.github.brer00.adventura.logika.Hra;
import com.github.brer00.adventura.logika.IHra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Kontroler, který zajištuje funkcionalitu hlavního menu hry
 */
public class MenuController extends GridPane  {

	@FXML private Button bNovaHra;
	@FXML private Button bKonec;
	@FXML private Button bNapoveda;
	
	private Stage primaryStage;

	@FXML
	public void novaHra() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../ui/hlavnifinal.fxml"));
		Parent root = loader.load();

		HomeController controller = loader.getController();
		IHra hra = new Hra();
		controller.inicializuj(hra);
		controller.setPrimaryStage(primaryStage);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Adventura | Hra");
	}
	
	@FXML
	public void konec(){
		primaryStage.close();  
        System.exit(0);
	}
	
	@FXML
	public void napoveda() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../ui/napoveda.fxml"));
		Parent root = loader.load();

		NapovedaController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		controller.init();

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Adventura | Nápověda");
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}