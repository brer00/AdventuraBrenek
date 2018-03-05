/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.logika;

/**
 * Třída Predmet představuje jednotlivé předměty (věci), které je možné
 * ve hře najít.
 * 
 * Předmět může být přenositelný nebo nepřenositelný. Přenositelné předměty
 * je možné vložit do batohu postavy a přenášet mezi lokacemi. Nepřenositelné
 * předměty není možné z lokace odnést.
 *
 * @author     Jan Riha, Radim Břenek
 * @version    LS 2016/2017
 */
public class Predmet
{
    private String nazev;
    private String popis;
    private boolean prenositelny;
    
    /**
     * Vytvoří nový předmět se zadaným názvem, popisem a přenositelností.
     * 
     * @param    nazev          název předmětu (jedno slovo)
     * @param    popis          popis předmětu (může se jednat o text libovolné délky)
     * @param    prenositelny   true, pokud má být předmět přenositelný; jinak false
     */
    public Predmet(String nazev, String popis, boolean prenositelny)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
    }

    /**
     * Vytvoří nový přenositelný předmět se zadaným názvem a popisem.
     * 
     * @param    nazev    název předmětu (jedno slovo)
     * @param    popis    popis předmětu (může se jednat o text libovolné délky)
     */
    public Predmet(String nazev, String popis)
    {
        this(nazev, popis, true);
    }
    
    /**
     * Vrátí název předmětu.
     * 
     * @returns    název předmětu
     */
    public String getNazev()
    {
        return nazev;
    }
    
    /**
     * Vrátí popis předmětu.
     * 
     * @returns    popis předmětu
     */
    public String getPopis()
    {
        return popis;
    }
    
    /**
     * Vrátí příznak, zda je předmět přenostilený, nebo ne.
     * 
     * @returns    true, pokud je předmět přenositelný; jinak false
     */
    public boolean isPrenositelny()
    {
        return prenositelny;
    }
    
    /**
     * Nastaví nový popis předmětu.
     * 
     * @param    popis nový popispředmětu
     */
    public void setPopis(String popis)
    {
        this.popis = popis;
    }
    
    /**
     * Nastaví přenositelnost předmětu.
     * 
     * @param    prenositelny   true, pokud má být předmět přenositelný; jinak false
     */
    public void setPrenositelny(boolean prenositelny)
    {
        this.prenositelny = prenositelny;
    }

    @Override
    public String toString()
    {
        return "Predmet: " + nazev;
    }

}
