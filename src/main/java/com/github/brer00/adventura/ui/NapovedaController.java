package com.github.brer00.adventura.ui;


import com.github.brer00.adventura.logika.Hra;
import com.github.brer00.adventura.logika.IHra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;


/**
 * Kontroler, který zprostředkovává nápovědu
 *
 */
public class NapovedaController extends GridPane  {

	@FXML private Button zpet;
	@FXML public WebView webView;

	private Stage primaryStage;

	
	public void init() throws IOException{

		WebEngine webEngine = webView.getEngine();
		webEngine.load(getClass().getResource("napoveda/UzivatelskaPrirucka.html").toExternalForm());	

	}
	
	
	@FXML
	void zpetDoMenu() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/menufinal.fxml"));
		Parent root = loader.load();

		MenuController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Adventura | Menu hry");
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
		
}