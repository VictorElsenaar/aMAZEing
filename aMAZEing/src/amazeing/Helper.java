/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;

/**
 *
 * @author vfelsenaar
 */
public class Helper extends Figuur{
    
    private int aantal;
    
    public Helper() {
        super(Color.ORANGE);
        aantal = 0;
        if(debug) {aantal = 1;}
    }
    public int getAantal() {
        return aantal;
    }
    public void toevoegenAantal() {
        this.aantal++;
    }
    public void gebruik() {
        this.aantal --;
        if (debug){System.out.println("Helper - Optimale Route aantal over : " + aantal);}
    }    
}
