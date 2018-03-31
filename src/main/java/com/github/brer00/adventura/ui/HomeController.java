package com.github.brer00.adventura.ui;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.github.brer00.adventura.logika.IHra;
import com.github.brer00.adventura.logika.Lokace;
import com.github.brer00.adventura.logika.Predmet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
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
	@FXML private ImageView imgPrdemet1;
	@FXML private ImageView imgPrdemet2;
	@FXML private ImageView imgPrdemet3;
	
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
			textVypis.appendText("\n" + hra.getHerniPlan().getAktualniLokace().dlouhyPopisKonec() + "\n" + hra.vratEpilog() + "\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		} 
	}

	/**
	 * incializace hry. Nastavi uvodni text, uvodni obrazky. Napoji observery
	 */
	public void inicializuj(IHra hra) {
        this.hra = hra;
        textVypis.setText(hra.vratUvitani());
        seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniLokace().getVychody());
        hra.getHerniPlan().addObserver(this);
        hra.getHerniPlan().getBatoh().addObserver(this);
        
		imgPrdemet1.setImage(null);
		imgPrdemet2.setImage(null);
		imgPrdemet3.setImage(null);	
		imgMapa.setImage(new Image("file:img/mapa_muzeum.png"));
		imgLokace.setImage(new Image("file:img/obrazek_muzeum.jpg"));
		
		// text hry se bude spravne zalamovat - nebude pretekat mimo
		textVypis.setWrapText(true);
	}


	/**
	 * Metoda reagujici na zmenu sledovanych objektu - provadi zmeny mapy, obrazku lokace a polozek v batohu
	 */
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
		List<Predmet> predmety = hra.getHerniPlan().getBatoh().getPredmety();		
		imgPrdemet1.setImage(null);
		imgPrdemet2.setImage(null);
		imgPrdemet3.setImage(null);
		switch (predmety.size()) {
		case 3:
			 image = new Image("file:img/" + predmety.get(2).getUrlObrazek());		
			 imgPrdemet3.setImage(image);	
			 System.out.println("img3");
		case 2:
			 image = new Image("file:img/" + predmety.get(1).getUrlObrazek());		
			 imgPrdemet2.setImage(image);
			 System.out.println("img2");
		case 1:
			 image = new Image("file:img/" + predmety.get(0).getUrlObrazek());		
			 imgPrdemet1.setImage(image);
			 System.out.println("img1");
		default:
			break;
		}
		
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
		primaryStage.setTitle("Adventura | Menu hry");
	}
	
	@FXML
	void konecHry() throws IOException{
		textVypis.appendText("\n\n Konec hry \n");
		textVstup.setDisable(true);
		odesli.setDisable(true);	
		konec.setDisable(true);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}