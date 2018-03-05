/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;

import logika.*;
import uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author     Jarmila Pavlíčková, Jan Říha, Radim Břenek
 * @version    LS 2016/2017
 */
public final class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra();
        TextoveRozhrani uihra = new TextoveRozhrani(hra);
        
        if (args.length > 0)
        {
            uihra.hrajZeSouboru(args[0]);
        }
        else
        {
            uihra.hraj();
        }
    }
    
    private Start() {}
}
