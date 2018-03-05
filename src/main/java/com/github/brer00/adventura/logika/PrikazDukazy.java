package com.github.brer00.adventura.logika;

/**
 * Trida Dukazy se stara o vypsani predmetu, ktere jsou v danou chvili v batohu.
 * 
 * @author     Radim Břenek
 * @version    17.5.2017
 */

class PrikazDukazy implements IPrikaz {
    private static final String NAZEV = "dukazy";
    private Batoh batoh;


    public PrikazDukazy(Batoh batoh) {
        this.batoh = batoh;
    }


    @Override
    public String proved(String... parametry) 
    {    
    	if (parametry.length == 0) {
    		// vypise se obsah batohu
            return batoh.toString();
        } else {
        	// příkaz "dukazy" se vola bez dalsich parametru
        	return "Moc příkazů. Zkus to prosím bez dalších parametrů";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
