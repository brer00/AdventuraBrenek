package com.github.brer00.adventura.logika;

/**
 * Trida ktera se stara o vyjmuti predmetu z batohu do aktualni lokace.
 * 
 * @author     Radim Břenek
 * @version    17.5.2017
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    
    private HerniPlan hPlan;
    

    public PrikazZahod(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }


    @Override
    public String proved(String... parametry)
    {
        if (parametry.length > 1)
        {
            return "Tomu nerozumím, nemůžeš zahodit vice veci najednou.";
        }
        
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if (parametry.length > 0)
        {
            String nazev = parametry[0];
            Batoh batoh = hPlan.getBatoh();
                  
            // pokud batoh obsahuje predmet 
            if (batoh.obsahujePredmet(nazev)) {
            	// najdi predmet
            	Predmet predmet = batoh.najdiPredmet(nazev);
            	// vyhod predmet z batohu
				batoh.odlozPredmet(predmet, aktLokace);
				return "Předmět " + nazev + " byl odhozen v " + aktLokace.getNazev();
			} 
            else
			{
				return "Předmět " + nazev + " se nepodařilo zahodit, protože není v batohu";
			}
  
        }
        else
        {
            // Zobraz popis aktualni lokace
            return aktLokace.dlouhyPopis();
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
	public String getNazev()
	{
	    return NAZEV;
	}
}
