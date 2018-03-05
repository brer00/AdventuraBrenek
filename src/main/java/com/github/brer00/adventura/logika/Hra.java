/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.logika;

/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída logiky aplikace. Třída vytváří instanci třídy HerniPlan, která inicializuje
 * mistnosti hry a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry. Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Radim Břenek
 * @version    LS 2016/2017
 */
public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDukazy(herniPlan.getBatoh()));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "\n" +
               "Vítejte!\n" +
               "V muzeu ve městě Bezpečnov byl ukraden z muzea velmi cenný obraz – Zrození Venouše.\n" +
               "Byli zadrženi tři podezřelí a tobě, jakožto hlavní postavě, byl přidělen velice\n" +
               "náročný úkol a to sice, že musíš určit, který z těchto podezřelých byl skutečným pachatelem\n" +
               "tohoto zločinu (pachatel je ve hře pouze jeden)..\n" +
               "Na začátek dostaneš pouze popis a informace o všech podezřelých.\n" +
               "Tak hodně štěstí, detektive!\n" +
               "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniLokace().dlouhyPopis();
    }
    
    /**
     * Vrátí závěrečnou zprávu pro hráče v závislosti na konci hry.
     */
    public String vratEpilog() {
        	
    	if(herniPlan.hracProhral())
    	{
    		return "Ajajaj, označil jsi špatného pachatele! Tady hra končí.";
    	} 
    	else if (herniPlan.hracVyhral())
    	{
    		return "Gratuluji, označil jsi správného pachatele! Tady hra končí";
    	}
    	else{
    		return "Dík, že jste si zahráli. Ahoj.";
    	}
	
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }
         

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param    radek  text, který zadal uživatel jako příkaz do hry.
     * @return   vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" ... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            
            if (herniPlan.hracVyhral() || herniPlan.hracProhral())
            {
                konecHry = true;
            } 
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *  
     * @param    konecHry hodnota false = konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     * @return    odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

