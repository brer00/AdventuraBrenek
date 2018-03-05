/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.brer00.adventura.logika;

/**
 * Trida reprezentuje postavu, se kterou je mozno komunikovat.
 * Kazda postava ma jmeno a promluvu, kterou muze hrac vyvolat skrze prikaz @PrikazMluv
 * 
 * @author     Radim Břenek
 * @version    19.5.2017
 */
public class Postava
{
    private String jmeno;
    private String promluva;


    public Postava(String jmeno, String promluva) {
		super();
		this.jmeno = jmeno;
		this.promluva = promluva;
	}


	public String getPromluva() {
		return promluva;
	}


	public void setPromluva(String promluva) {
		this.promluva = promluva;
	}


	public String getJmeno() {
		return jmeno;
	}


	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}


	@Override
    public String toString()
    {
        return "Postava: " + jmeno;
    }

}
