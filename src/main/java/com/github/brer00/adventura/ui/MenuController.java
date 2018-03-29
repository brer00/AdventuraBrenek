package com.github.brer00.adventura.ui;


import com.github.brer00.adventura.logika.Hra;
import com.github.brer00.adventura.logika.IHra;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 *
 */
public class MenuController extends GridPane  {


	@FXML private Button bNovaHra;
	private Stage primaryStage;


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
		primaryStage.setTitle("Základní adventura");

	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}