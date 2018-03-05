package com.github.brer00.adventura.logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author     Luboš Pavlíček, Jan Říha, Radim Břenek
 * @version    LS 2016/2017
 */
public class SeznamPrikazuTest
{
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazVezmi prVezmi;
    private PrikazProzkoumej prProzkoumej;
    private PrikazMluv prMluv;
    private PrikazZahod prZahod;
    private PrikazDukazy prDukazy;
    
    @Before
    public void setUp() {
        Hra hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
        prVezmi = new PrikazVezmi (hra.getHerniPlan());
        prProzkoumej = new PrikazProzkoumej (hra.getHerniPlan());
        prMluv = new PrikazMluv (hra.getHerniPlan());
        prZahod = new PrikazZahod (hra.getHerniPlan());
        prDukazy = new PrikazDukazy (hra.getHerniPlan().getBatoh());
    }

    /**
     * Testuje zda-li se vraci zadany prikaz
     */
    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prVezmi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(prVezmi, seznPrikazu.vratPrikaz("vezmi"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
    }
    
    /**
     * Testuje zda-li je prikaz v seznamu prikazu platny
     */
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prProzkoumej);
        seznPrikazu.vlozPrikaz(prMluv);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("prozkoumej"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("mluv"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    /**
     * Testuje jestli se spravne zobrazuji nazvy prikazu
     */
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prZahod);
        seznPrikazu.vlozPrikaz(prDukazy);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(true, nazvy.contains("zahod"));
        assertEquals(true, nazvy.contains("dukazy"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    
}
