/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.uiText;

import java.io.*;
import java.util.Scanner;
import com.github.brer00.adventura.logika.IHra;

/**
 * Class TextoveRozhrani
 *
 * Toto je třída uživatelského rozhraní aplikace Adventura. Třída vytváří instanci třídy Hra,
 * která představuje logiku aplikace. Čte vstup zadaný uživatelem a předává tento řetězec logice
 * a vypisuje odpověď logiky na konzoli.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     * Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     * Hlavní metoda hry pro hraní z konzole. Vypíše úvodní text a pak opakuje
     * čtení a zpracování příkazu od hráče do konce hry (dokud metoda konecHry()
     * z logiky nevrátí hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());
        System.out.println();
        
        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
            System.out.println();
        }

        System.out.println(hra.vratEpilog());
    }
    
    /**
     * Metoda přečte příkaz z příkazového řádku
     *
     * @return    vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
