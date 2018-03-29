package com.github.brer00.adventura.ui;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import com.github.brer00.adventura.logika.Hra;
import com.github.brer00.adventura.logika.IHra;
import com.github.brer00.adventura.logika.Lokace;
import com.github.brer00.adventura.logika.Predmet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 *
 */
public class HomeController extends GridPane implements Observer {

	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private Button zpet;
	@FXML private Button konec;
	@FXML private ListView<Lokace> seznamMistnosti;
	@FXML private ImageView imgLokace;
	@FXML private ImageView imgMapa;
	private IHra hra;
	private Stage primaryStage;


	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {

		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");

		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}

	}

	public void inicializuj(IHra hra) {
        this.hra = hra;
        textVypis.setText(hra.vratUvitani());
        seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
        hra.getHerniPlan().addObserver(this);
	}


	public void update(Observable o, Object arg) {		
		
		// vypise se aktualni seznam vychodu
		seznamMistnosti.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());

		// zmeni se obrazek aktualni lokace
		Image image = new Image("file:img/" + hra.getHerniPlan().getAktualniLokace().getUrlObrazku());		
		imgLokace.setImage(image);
		
		// aktualizace mapy
		image = new Image("file:img/" + hra.getHerniPlan().getAktualniLokace().getUrlMapa());		
		imgMapa.setImage(image);
		
		
		// aktualizace batohu
		Predmet p = hra.getHerniPlan().getBatoh().getPredmety().get(0) ;
		
		
		
		
		
			
	}



	@FXML
	void zpetDoMenu() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../ui/menufinal.fxml"));
		Parent root = loader.load();

		MenuController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		primaryStage.setTitle("Základní adventura");
	}
	
	@FXML
	void konecHry() throws IOException{
		textVypis.appendText("\n\n Konec hry \n");
		textVstup.setDisable(true);
		odesli.setDisable(true);	
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}