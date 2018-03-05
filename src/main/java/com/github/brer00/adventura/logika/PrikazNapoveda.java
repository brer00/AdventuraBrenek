/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha, Radim Břenek
 * @version    LS 2016/2017
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

   /**
    * Konstruktor třídy
    *
    * @param    platnePrikazy seznam příkazů, které je možné ve hře použít, aby je nápověda mohla zobrazit uživateli.
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return    napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je zjistit, který z podezřelých byl skutečným pachatelem krádeže v muzeu. \n"
        + "Dosáhneš toho tak, že si prohlédneš všechny indicie (předměty), které by mohly mít co dočinění s krádeží. \n"
        + "Nezapomeň se také zeptat lidí, co potkáš, třeba byli svědky události a mohou ti nějak pomoci. \n"
        + "Pokud jsi myslíš, že už jsi na pachatele přišel, tak zamiř rovnou na policejní stanici a udělej konečný verdikt. \n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
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
