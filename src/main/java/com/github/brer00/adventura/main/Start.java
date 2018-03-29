/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.main;

import com.github.brer00.adventura.ui.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.github.brer00.adventura.logika.Hra;
import com.github.brer00.adventura.logika.IHra;
import com.github.brer00.adventura.ui.HomeController;
import com.github.brer00.adventura.ui.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author     Jarmila Pavlíčková, Jan Říha, Radim Břenek
 * @version    LS 2016/2017
 */
public class Start extends Application
{


    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

    	if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }

        //		  TODO parametrické spuštění hry
//        IHra hra = new Hra();
//        TextoveRozhrani ui = new TextoveRozhrani(hra);
//        ui.hraj();
    	
    	//launch(args);
    }
    
    /**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {



        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("../ui/hlavnifinal.fxml"));
        loader.setLocation(getClass().getResource("../ui/menufinal.fxml"));

        Parent root = loader.load();

    	//HomeController controller = loader.getController();
        MenuController controller = loader.getController();
    	IHra hra = new Hra();
		//controller.inicializuj(hra);
        controller.setPrimaryStage(primaryStage);

    	
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("Základní adventura");

	}




	
}
