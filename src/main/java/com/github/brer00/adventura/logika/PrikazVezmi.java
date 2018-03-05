package com.github.brer00.adventura.logika;


/**
 * Třída představuje příkaz pro sebrání předmětu z aktuální lokace
 * a jeho vložení do batohu (inventáře) postavy.
 * 
 * @author     Jan Riha, Radim Břenek
 * @version    LS 2016/2017
 */
public class PrikazVezmi implements IPrikaz
{
    private static final String NAZEV = "vezmi";
    
    private HerniPlan hPlan;
    
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }
    
    /**
     * Metoda představuje zpracování příkazu pro sebrání předmětu.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr, ověří, zda v aktuální lokaci je předmět s tímto
     * názvem, zda je přenositelný, zda je v batohu místo a
     * následně předmět odebere z lokace a vloží ho do batohu.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {        
        if (parametry.length < 1)
        {
            return "Nevím, co mám sebrat";
        }
        
        if (parametry.length > 1)
        {
            return "Tomu nerozumím, nedokážu sebrat více věcí najednou";
        }
        
        String nazevPredmetu = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if (!aktLokace.obsahujePredmet(nazevPredmetu))
        {
            return "Předmět " + nazevPredmetu + " tady není";
        }
  
        Predmet predmet = aktLokace.vezmiPredmet(nazevPredmetu);
        if (!predmet.isPrenositelny())
        {
            aktLokace.vlozPredmet(predmet);
            return "Předmět " + nazevPredmetu + " fakt neuneseš";
        }
             
        Batoh batoh = hPlan.getBatoh();
        if(batoh.jePlny())
        {
        	return "V batohu už nemáš volné místo, musíš něco zahodit";
        }
        else
        {
        	batoh.vlozPredmet(predmet);
        	return "Uložil(a) jsi předmět " + nazevPredmetu + " do batohu";
        }
         
    }
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
	public String getNazev()
	{
	    return NAZEV;
	}
    
}
