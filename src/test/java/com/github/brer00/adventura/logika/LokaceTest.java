package com.github.brer00.adventura.logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída LokaceTest slouží ke komplexnímu otestování
 * třídy Lokace
 *
 * @author     Jarmila Pavlickova, Jan Riha
 * @version    LS 2016/2017
 */
public class LokaceTest
{
    private static final String OTISK_BOTY = "otisk_boty";
    private static final String FONTANA = "fontana";
    
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        // k opravení PMD chyby
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
        // k opravení PMD chyby
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry.  
     */
    @Test
    public  void testLzeProjit() {		
        Lokace lokace1 = new Lokace("namesti", "namesti_popis", "");
        Lokace lokace2 = new Lokace("hospoda", "hospoda_popis","");
        lokace1.setVychod(lokace2);
        lokace2.setVychod(lokace1);
        assertEquals(lokace2, lokace1.vratSousedniLokaci("hospoda"));
        assertEquals(null, lokace2.vratSousedniLokaci("park"));
    }
    
    /**
     * Testuje zda jsou v lokaci umisteny spravne predmety.
     */
    @Test
    public void testVeci()
    {
        Lokace lokace1 = new Lokace("park", "park_popis","");
        Predmet predmet1 = new Predmet(OTISK_BOTY, "otisk_boty_popis", true);
        Predmet predmet2 = new Predmet(FONTANA, "fontana_popis", false);
        lokace1.vlozPredmet(predmet1);
        lokace1.vlozPredmet(predmet2);
        assertTrue(lokace1.obsahujePredmet(OTISK_BOTY));
        assertSame(predmet1, lokace1.najdiPredmet(OTISK_BOTY));
        assertSame(predmet1, lokace1.vezmiPredmet(OTISK_BOTY));
        assertTrue(lokace1.obsahujePredmet(FONTANA));
        assertSame(predmet2, lokace1.najdiPredmet(FONTANA));
        assertSame(predmet2, lokace1.vezmiPredmet(FONTANA));
        assertFalse(lokace1.obsahujePredmet("obraz"));
        assertNull(lokace1.najdiPredmet("obraz"));
        assertNull(lokace1.vezmiPredmet("obraz"));
    }

}
