/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.logika;

/**
 * Trida diky ktere lze ziskat promluvu od jedne postavy, ktera se nachazi v aktualni lokaci
 * 
 * @author     Radim Břenek
 * @version    19.5.2017
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "mluv";
    
    private HerniPlan hPlan;
    

    public PrikazMluv(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }


    @Override
    public String proved(String... parametry)
    {
        if (parametry.length > 1)
        {
            return "Tomu nerozumím, nemůžeš mluvit s více lidmi najednou.";
        }
        
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if (parametry.length > 0)
        {
            String jmeno = parametry[0];
            
            if (aktLokace.obsahujePostavu(jmeno)){
            	// vrati promluvu od pozadovane postavy
            	return aktLokace.najdiPostavu(jmeno).getPromluva();
            }

            return "Postava " + jmeno + " není v aktuální lokaci.";
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
