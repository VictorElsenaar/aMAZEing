package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Victor
 */
public class Bazooka extends Figuur{
    
    private int ammo;
    
    // Constructor
    public Bazooka() {
        super(Color.CYAN); //Color(0,255,255)
        ammo = 0;
        if(debug) {ammo = 99;}
    }
    public int getAmmo() {
        return ammo;
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    public void toevoegenAmmo() {
        ammo++;
    }
    public ArrayList<Vak> fire(String richting, ArrayList<Vak> doolhofMap, int current_maze_size, Vak spelersVak, int position_change_amount) {
        int currentLocationIndex = doolhofMap.indexOf(spelersVak);
        Vak schietvak = doolhofMap.get(currentLocationIndex+position_change_amount);
        int i = 1;
        while(!schietvak.isMuur(schietvak)) {
            schietvak = doolhofMap.get(currentLocationIndex+(position_change_amount*i));
            i++;
        }
        if(schietvak.isMuur(schietvak)) { 
            // muur vak gevonden dus afhandelen.
            if(debug) {System.out.println("vakje is een muur, dus kogel afhandelen");}
            Muur muur = (Muur) schietvak.getFiguur();
            if(muur.getBorderMuur()){
                if(debug) {System.out.println("Bordermuur kan niet kapot");}
                // Bordermuur dus kogel is verloren, animatie moet het duidelijk maken
            } else {
                if(debug) {System.out.println("Normale muur is stuk!");}
                Figuur empty = new Leeg();
                schietvak.setFiguur(empty);
            }
        }
        ammo --;
        if (debug){System.out.println("Ammo nog over: " + ammo);}
        return doolhofMap;
    }
}
