/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.logika;

import java.util.Observable;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Radim Břenek
 * @version    LS 2016/2017
 */
public class HerniPlan extends Observable {

    private static final String VITEZNA_LOKACE = "cela2";
	private static final String PROHERNI_LOKACE_1 = "cela1";
	private static final String PROHERNI_LOKACE_2 = "cela3";
	private static final int KAPACITA_BATOHU = 3;
    
    private Lokace aktualniLokace;
    private Batoh batoh;
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public HerniPlan() {
        zalozLokaceHry();
        this.batoh = new Batoh(KAPACITA_BATOHU);
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví muzeum
     */
    private void zalozLokaceHry() {
        // vytvářejí se jednotlivé lokace      
        Lokace muzeum = new Lokace("muzeum", "Tak a můžeme začít! Tady máš ten slibovaný popis podezřelých:\n" +
        "Tomáš Jedno (cela 1) - věk 30, výška 185 cm, silnější postavy, hnědé krátké vlasy, povoláním advokát, koníček - šachy\n" +
        "Pavel Brut (cela 2) - věk 28, výška 190 cm, štíhlé postavy, blond krátké vlasy, povoláním automechanik, vášnivý sportovec - běžec\n" +
        "Jarda Malý (cela 3) - věk 38, výška 150 cm, štíhlé šlachovité postavy, blond dlouhé vlasy, povoláním akademický malíř, horolezec", "obrazek_muzeum.jpg");
        Lokace namesti = new Lokace("namesti", "Tak jsem vyšel z muzea na náměstí a hned tu vidím spousty zajímavých věcí. Jo a hned vedle náměstí je hospoda, tam bych se měl jít podívat hned jak to tu prozkoumám.", "obrazek_namesti.jpg"); 
        Lokace hospoda = new Lokace("hospoda", "To je teda ale pajzl! A hele, támhle sedí místní štamgasta Pepa, ten je tu vždycky od večera do rána, tak se ho zkusím zeptat, jestli náhodou něco neviděl.", "obrazek_hospoda.jpg"); 
        Lokace gangsterskaCtvrt = new Lokace("gangsterska_ctvrt", "Tohle je vyhlášená gangsterská čtvrť našeho města, jestli tady něco podezřelého nenajdu, tak už nikde... Je to tu fakt drsný, až odsud odejdu, tak se budu muset jít trochu uklidnit do parku", "obrazek_gangsterskactvrt.jpg"); // jednoslovný krátký název
        Lokace park = new Lokace("park", "Nádherná příroda, jupí! A támhle jde místní drbna Nováková, ta už tak nádherná není, ale mohla by něco vědět!", "obrazek_park.jpg"); 
        Lokace zastavarna = new Lokace("zastavarna", "Pachatel si asi nenechá obraz viset na zdi, ale bude se ho hodně rychle snažit prodat, tak se tu porozhlídnu, třeba tu bude.", "obrazek_zastavarna.jpg"); 
        Lokace policejniStanice = new Lokace("policie", "Tak inspektore, tady máš pro připomenutí popis všech podezřelých:\n" +
        "Tomáš Jedno (cela 1) - věk 30, výška 185 cm, silnější postavy, hnědé krátké vlasy, povoláním advokát, koníček - šachy\n" +
        "Pavel Brut (cela 2) - věk 28, výška 190 cm, štíhlé postavy, blond krátké vlasy, povoláním automechanik, vášnivý sportovec - běžec\n" +
        "Jarda Malý (cela 3) - věk 38, výška 150 cm, štíhlé šlachovité postavy, blond dlouhé vlasy, povoláním akademický malíř, horolezec\n" +
        "Teď ho porovnej se všemi nasbíranými důkazy a jdi jedno z nich obvinit do jeho cely!", "obrazek_policejnistanice.jpg");  
        Lokace cela1 = new Lokace("cela1", "Vždyť mám hnědé vlasy, tak proč bych používal hnědou paruku?! Navíc jsi v kukle našel blonďatý vlas.. A taky opravdu nevím, kde bych se ušpil od barvy nebo oleje..", "obrazek_cela1.jpg"); 
        Lokace cela2 = new Lokace("cela2", "Dobře, přiznávám se, byl jsem to já!", "obrazek_cela2.jpg"); 
        Lokace cela3 = new Lokace("cela3", "Jsi ten nejhorší detektiv na světě, vždyť já jsem to nemohl být ani přinejmenším!", "obrazek_cela3.jpg"); 
        
        muzeum.setUrlMapa("mapa_muzeum.png");
        namesti.setUrlMapa("mapa_namesti.png");
        hospoda.setUrlMapa("mapa_hospoda.png");
        gangsterskaCtvrt.setUrlMapa("mapa_gangsterskactvrt.png");
        park.setUrlMapa("mapa_park.png");
        zastavarna.setUrlMapa("mapa_zastavarna.png");
        policejniStanice.setUrlMapa("mapa_policejnistanice.png");
        cela1.setUrlMapa("mapa_cela1.png");
        cela2.setUrlMapa("mapa_cela2.png");
        cela3.setUrlMapa("mapa_cela3.png");
        
       
        // přiřazují se průchody mezi lokacemi (sousedící lokace)
        muzeum.setVychod(namesti);  
        namesti.setVychod(muzeum);
        namesti.setVychod(hospoda);
        namesti.setVychod(gangsterskaCtvrt);        
        hospoda.setVychod(namesti);     
        gangsterskaCtvrt.setVychod(namesti);      
        gangsterskaCtvrt.setVychod(park);
        gangsterskaCtvrt.setVychod(zastavarna);
        gangsterskaCtvrt.setVychod(policejniStanice);
        park.setVychod(gangsterskaCtvrt);
        zastavarna.setVychod(gangsterskaCtvrt);   
        policejniStanice.setVychod(gangsterskaCtvrt);
        policejniStanice.setVychod(cela1);
        policejniStanice.setVychod(cela2);
        policejniStanice.setVychod(cela3);
        
        // vytvoření předmětů a jejich umístění do patřičných lokací 
        Predmet socha = new Predmet("socha", "Á, to je někdo známý. Už vím, Sókratés. Ten mi nepomůže, nemůže mluvit. A jeho odpověď stejně znám „Vím, že nic nevím“.", false);
        namesti.vlozPredmet(socha);
        Predmet penezenka = new Predmet("penezenka", "To jsem ale nepozorný, vypadla mi peněženka! Né, ta není moje. V téhle jsou peníze. Ale co to? Mezi kartičkami jedna chybí..", true);
        namesti.vlozPredmet(penezenka);
        Predmet karticka = new Predmet("karticka", "Mám ji. Našel jsem chybějící kartičku z peněženky. Je to občanský průkaz paní Dvořákové. Tu asi ale pachatel neztratil.", true);
        namesti.vlozPredmet(karticka);            
        Predmet paruka = new Predmet("paruka", "Mohl ji mít pachatel? Á, má hnědou barvu! To je podezřelé.", true);
        gangsterskaCtvrt.vlozPredmet(paruka);
        Predmet kukla = new Predmet("kukla", "Musím ji pořádně prohlédnout, abych zajistil všechny stopy. Našel jsem krátký blond vlas.", true);
        gangsterskaCtvrt.vlozPredmet(kukla);
        Predmet ramObrazu = new Predmet("ram_obrazu", "Uf, to je rám z hledaného obrazu. Je pěkně špinavý. Vypadá to že je od nějaké barvy, nebo oleje.", true);
        gangsterskaCtvrt.vlozPredmet(ramObrazu);
        Predmet otiskBoty = new Predmet("otisk_boty", "To bude ta stopa, na kterou mě navedla paní Nováková. Ta stopa je evidentně od vysokého člověka, takže pivař Pepa zřejmě neříkal pravdu.", true);
        park.vlozPredmet(otiskBoty);
        Predmet fontana = new Predmet("fontana", "Nenajdu tady nějaký důkaz? Á zlatá rybka tady plave, kéž by mi pomohla.", false);
        park.vlozPredmet(fontana);        
        Predmet obraz = new Predmet("obraz", "Našel jsem ho! To je ten ukradený obraz! A má jiný rám.", true);
        zastavarna.vlozPredmet(obraz);
        Predmet bankovka = new Predmet("bankovka", "Ta bankovka je špinavá! Černá barva – stejná jako na odhozeném rámu obrazu v gangsterké čtvrti.", true);
        zastavarna.vlozPredmet(bankovka);
                
        // vytvoreni postav a jejich umisteni
        Postava pivar = new Postava("pivar_pepa", "Klouzek: Neviděl jste někoho s obrazem?\n" +
        "Pepa: Jojo, viděl jsem támle na náměstí běžet chlapa.\n" +
        "Klouzek: Popiště mi ho!\n" +
        "Pepa: Byl poměrně malý, tak 140 cm. Tlustej jako bečka a měl hnědé vlasy.");
        hospoda.vlozPostavu(pivar);
        Postava drbna = new Postava("drbna_novakova", "Klouzek: Neviděla jste tady někoho s obrazem?\n" +
        "Nováková: Viděla. Proběhl tady parkem.\n" +
        "Klouzek: A můžete mi ho popsat?\n" +
        "Nováková: Byl štíhlý, vysoký a strašně rychle utíkal. Běžel přes záhonek růží, nezanechal tam náhodou stopu?");
        park.vlozPostavu(drbna);
        Postava zastavan = new Postava("zastavan_zastavarovic", "Klouzek: Byl tady někdo prodat obraz?\n" +
        "Zastavarovič: Ano a teď nám tady visí na zdi. Samozřejmě tedy ten obraz, ne ten člověk.\n" +
        "Klouzek: Popište mi toho, kdo obraz přinesl, prosím.\n" +
        "Zastavarovič: Byl vysoký, hubený, hnědé vlasy, v kapse měl asi schovanou kuklu a hodně špinavé ruce. Zřejmě předtím opravoval auto?");
        zastavarna.vlozPostavu(zastavan);
             
        aktualniLokace = muzeum;  // hra začíná v muzeu      
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public void setAktualniLokace(Lokace lokace) {
       aktualniLokace = lokace;
       this.setChanged();
       this.notifyObservers();
    }
    
    /**
     * Metoda vrací informaci, zda hráč vyhrál.
     * 
     * @return    true, pokud hráč vyhrál; jinak false
     */
    public boolean hracVyhral() {
        return aktualniLokace.getNazev().equals(VITEZNA_LOKACE);
    }

    /**
     * Metoda vrací informaci, zda hráč prohral. 
     * prohra nastane v pripade, kdy hrac vstoupi do nektere proherni lokace
     * @return true, pokud hrac prohral, jinak false
     */
	public boolean hracProhral() {
		return (aktualniLokace.getNazev().equals(PROHERNI_LOKACE_1) 
				|| aktualniLokace.getNazev().equals(PROHERNI_LOKACE_2));
	}
    
    /**
     * Vraci odkaz na hracuv batoh
     */
	public Batoh getBatoh() {
		return batoh;
	}
	
}
