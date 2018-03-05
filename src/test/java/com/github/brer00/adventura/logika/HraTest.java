package com.github.brer00.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author     Jarmila Pavlíčková, Jan Říha, Radim Břenek
 * @version    LS 2016/2017
 */
public class HraTest {
    private Hra hra1;
    
    private static final String JDI_NAMESTI = "jdi namesti";
    private static final String VEZMI_KARTICKA = "vezmi karticka";
    private static final String KARTICKA = "karticka";
    private static final String KONEC = "konec";

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
        hra1 = new Hra();
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

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("muzeum", hra1.getHerniPlan().getAktualniLokace().getNazev());
        
        hra1.zpracujPrikaz(JDI_NAMESTI);
        assertEquals(false, hra1.konecHry());
        assertEquals("namesti", hra1.getHerniPlan().getAktualniLokace().getNazev());

        hra1.zpracujPrikaz(VEZMI_KARTICKA);
        assertEquals(false, hra1.konecHry());
        assertEquals("namesti", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet(KARTICKA));
                
        hra1.zpracujPrikaz("jdi hospoda");
        assertEquals(false, hra1.konecHry());
        assertEquals("hospoda", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePostavu("pivar_pepa"));
        
        hra1.zpracujPrikaz(KONEC);
        assertEquals(true, hra1.konecHry());
    }
    
    /**
     * Projde celou mapu nejkratsi cestou do vitezne lokace.
     * Testuje, zda nastal konec hry a hrac vyhral
     */
    @Test
    public void testVyhry(){
    	hra1.zpracujPrikaz(JDI_NAMESTI);
    	hra1.zpracujPrikaz("jdi gangsterska_ctvrt");
    	hra1.zpracujPrikaz("jdi policie");
    	hra1.zpracujPrikaz("jdi cela2");
    	assertEquals(true, hra1.konecHry());
    	assertEquals(true, hra1.getHerniPlan().hracVyhral());
    }
    
    /**
     * Projde celou mapu nejkratsi cestou do jedne proherni lokace.
     * Testuje, zda nastal konec hry a hrac prohral
     */
    @Test
    public void testProhry(){
    	hra1.zpracujPrikaz(JDI_NAMESTI);
    	hra1.zpracujPrikaz("jdi gangsterska_ctvrt");
    	hra1.zpracujPrikaz("jdi policie");
    	hra1.zpracujPrikaz("jdi cela1");
    	assertEquals(true, hra1.konecHry());
    	assertEquals(true, hra1.getHerniPlan().hracProhral());
    }
    
    /**
     * Testuje, zda je nemozne zvednout neprenosny predmet
     */
    @Test
    public void testZvednutiNeprenosneho() {      	
        hra1.zpracujPrikaz(JDI_NAMESTI);
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("socha"));
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());
        
        hra1.zpracujPrikaz("vezmi socha");           
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet("socha"));
        hra1.zpracujPrikaz(KONEC);

    }    
    
    /**
     * Testuje, zda je mozne zvednout predmet
     */
    @Test
    public void testZvednutiPredemetu() {      	
        hra1.zpracujPrikaz(JDI_NAMESTI);
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet(KARTICKA));
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());
        
        hra1.zpracujPrikaz(VEZMI_KARTICKA);           
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet(KARTICKA));
        hra1.zpracujPrikaz(KONEC);

    }     
    
    /**
     * Testuje zahazovani predmetu z batohu
     */
    @Test
    public void testVyhozeniPredmetu() {      	
        hra1.zpracujPrikaz(JDI_NAMESTI);
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet(KARTICKA));
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());
        
        hra1.zpracujPrikaz(VEZMI_KARTICKA);           
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet(KARTICKA));
        
        hra1.zpracujPrikaz("zahod karticka");           
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet(KARTICKA));      
               
        hra1.zpracujPrikaz(KONEC);

    }    
    
    /**
     * Komplexni metoda. Testuje, zda pri naplneni batohu na hodnotu kapacity nebude mozne pridavat dalsi predmety
     */
    @Test
    public void testPreplneniBatohu() {      
    	
    	// pocita se s kapacitou 3 predmety
    	assertEquals(3, hra1.getHerniPlan().getBatoh().getKapacita());
    	
        hra1.zpracujPrikaz(JDI_NAMESTI);
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet(KARTICKA));
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());
          
        hra1.zpracujPrikaz(VEZMI_KARTICKA);           
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet(KARTICKA));
              
        hra1.zpracujPrikaz("jdi gangsterska_ctvrt");
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("paruka"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("kukla"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("ram_obrazu"));
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());

        hra1.zpracujPrikaz("vezmi paruka"); 
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("paruka"));
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny());        
        
        hra1.zpracujPrikaz("vezmi kukla"); 
        assertEquals(true, hra1.getHerniPlan().getBatoh().obsahujePredmet("kukla"));
        assertEquals(true, hra1.getHerniPlan().getBatoh().jePlny()); // nyni je jiz batoh plny
        
        hra1.zpracujPrikaz("vezmi ram_obrazu"); 
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet("ram_obrazu")); // 4. predmet uz se nepodariloo ulozit
        
        hra1.zpracujPrikaz("zahod karticka");  // vyhodime karticku a uvolnime tak jedno misto  
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet(KARTICKA)); 
        assertEquals(false, hra1.getHerniPlan().getBatoh().jePlny()); 
        
        hra1.zpracujPrikaz("vezmi ram_obrazu"); // nyni se jiz predmet zvednout podari
        assertEquals(false, hra1.getHerniPlan().getBatoh().obsahujePredmet("ram_obrazu"));
           
        hra1.zpracujPrikaz(KONEC);  
    }   
    


    
}
