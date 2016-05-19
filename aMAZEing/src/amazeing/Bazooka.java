package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.LinkedList;

/**
 *
 * @author Victor
 */
public class Bazooka extends Figuur{
    private int ammo;
    public Bazooka() {
        super("bazooka", Color.CYAN);
        ammo = 0;
    }
    public int getAmmo() {
        return ammo;
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    public void reload(Bazooka bazooka) {
        this.ammo --;
        if (debug){System.out.println("Ammo nog over: " + ammo);}
    }
    /*
    public LinkedList<Vak> fire(String richting, LinkedList<Vak> doolhofMap, int mazesize, Vak spelersVak) {
        int positionchange = 0;
        switch(richting) {
            case "right":
                positionchange = 1;
                break;
            case "left":
                positionchange = -1;
                break;
            case "up":
                positionchange = -mazesize;
                break;
            case "down":
                positionchange = mazesize;
                break;
        }
        int currentLocationIndex = doolhofMap.indexOf(spelersVak);
        Vak schietvak = doolhofMap.get(currentLocationIndex+positionchange);
        int i = 1;
        while(!isMuur(schietvak)) {
            schietvak = doolhofMap.get(currentLocationIndex+(positionchange*i));
            i++;
        }
        if(isMuur(schietvak)) { 
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
        return doolhofMap;
    }   
    public boolean isMuur(Vak nieuwevak){
        if(nieuwevak.getFiguur().getNaam().equals("muur") || nieuwevak.getFiguur().getNaam().equals("buitenmuur") ) {
            return true;
        }
        return false;
    } */
    
}
