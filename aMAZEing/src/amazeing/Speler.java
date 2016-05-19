/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author vic
 */
public class Speler extends Figuur {
    //private Level level;
    private Bazooka bazooka = new Bazooka();
    //private int bazooka_count;
    public Speler() {
        super("speler", Color.BLUE);
        //bazooka_count = 0;
    }
    /*
    public void addBazooka() {
        this.bazooka_count++;
    }
    public int getCount() {
        return bazooka_count;
    }*/
    public LinkedList<Vak> move(String richting, LinkedList<Vak> doolhofMap, int mazesize, Vak spelersVak) {
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
        
        int tempindex=0;
        tempindex = doolhofMap.indexOf(spelersVak);
        Vak oudeVak = doolhofMap.get(tempindex);
        Vak nieuweVak = doolhofMap.get(tempindex+positionchange);
        if (isVriend(nieuweVak)) {
             JOptionPane.showMessageDialog(null, "Vriend gevonden! gefeliciteerd!");
        }
        
        if(!isMuur(nieuweVak)) {
            // Oude vak speler ophalen
            Speler huidigeSpeler = (Speler) oudeVak.getFiguur();

            // OUDE VAK LEEG MAKEN
            Figuur empty = new Leeg();
            oudeVak.setFiguur(empty);
            doolhofMap.set(tempindex, oudeVak);
            
            // controleer of er op het vak iets anders staat, voor nu alleen bazooka
            if(isBazooka(nieuweVak)) {
                //huidigeSpeler.addBazooka();
                bazooka.setAmmo(bazooka.getAmmo()+1);
                System.out.println("#########"+bazooka.getAmmo());
            }
            //System.out.println("Aantal bazooka's = " + huidigeSpeler.getCount());
            
            // nieuwevak spelers object in plaatsen LET OP NOG NAKIJKEN OF ER NOG OPPAKBARE DINGEN LIGGEN
            nieuweVak.setFiguur(huidigeSpeler);
            doolhofMap.set(tempindex+positionchange,nieuweVak);
        }    
        return doolhofMap;
    }
    public boolean isBazooka(Vak nieuwevak) {
        if(nieuwevak.getFiguur().getNaam().equals("bazooka")) {
            return true;
        }
        return false;
    }
    public boolean isMuur(Vak nieuwevak){
        if(nieuwevak.getFiguur().getNaam().equals("muur") || nieuwevak.getFiguur().getNaam().equals("buitenmuur") ) {
            return true;
        }
        return false;
    }
    public boolean isVriend(Vak nieuwevak) {
        if(nieuwevak.getFiguur().getNaam().equals("vriend")) {
            return true;
        }
        return false;
    }
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
        if (bazooka.getAmmo() > 0) {
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
            bazooka.reload(bazooka);
        } else {
            if (debug) {System.out.println("Geen ammo.");}
        }
        return doolhofMap;
    }   
}
