/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author vic
 */
public class Speler extends Figuur {

    private Bazooka bazooka = new Bazooka();
    private Helper helper = new Helper();
    private Vak huidigeVak;

    // Constructor
    public Speler() {
        super( Color.BLUE); //Color(0,0,255)
        //super(new Color(0,32,255)); //Color(0,0,255)
        kleur1 = new Color(0,32,255);
        kleur2 = new Color(0,52,255);
    }
    // Let op richting bepaling staat ook in OptimaleRoute!
    public int positionchange(String richting, int current_maze_size) {
        int position_change_amount = 0;
        switch(richting) {
            case "right":
                position_change_amount = 1;
                break;
            case "left":
                position_change_amount = -1;
                break;
            case "up":
                position_change_amount = -current_maze_size;
                break;
            case "down":
                position_change_amount = current_maze_size;
                break;
        }        
        return position_change_amount;
    }
    
    public ArrayList<Vak> move(String richting, ArrayList<Vak> doolhofMap, int current_maze_size, Vak spelersVak) {
        int position_change_amount = positionchange(richting, current_maze_size);
        int tempindex=0;
//        int x = 0;
//        int y = 0;
        tempindex = doolhofMap.indexOf(spelersVak);
        Vak oudeVak = doolhofMap.get(tempindex);
        Vak nieuweVak = doolhofMap.get(tempindex+position_change_amount);
//        if (nieuweVak.isVriend(nieuweVak)) {
//             JOptionPane.showMessageDialog(null, "Vriend gevonden! gefeliciteerd!");
//        }
//        
        if(!nieuweVak.isMuur(nieuweVak)) {
            // Oude vak speler ophalen
            Speler huidigeSpeler = (Speler) oudeVak.getFiguur();

            // OUDE VAK LEEG MAKEN
            Figuur empty = new Leeg();
            oudeVak.setFiguur(empty);
            doolhofMap.set(tempindex, oudeVak);
            
            if(nieuweVak.isBazooka(nieuweVak)) {
                bazooka.setAmmo(bazooka.getAmmo()+1);
                System.out.println("#########"+bazooka.getAmmo());
            }
            if(nieuweVak.isHelper(nieuweVak)) {
                helper.toevoegenAantal();
            }
            nieuweVak.setFiguur(huidigeSpeler);
            doolhofMap.set(tempindex+position_change_amount,nieuweVak);
            huidigeVak = doolhofMap.get(tempindex+position_change_amount);
        }
        return doolhofMap;
    }
    public ArrayList<Vak> fire(String richting, ArrayList<Vak> doolhofMap, int current_maze_size, Vak spelersVak) {
        int position_change_amount = positionchange(richting, current_maze_size);
        if (bazooka.getAmmo() > 0) {
            this.bazooka.fire(richting, doolhofMap, current_maze_size, spelersVak, position_change_amount);
        } else {
            if (debug) {System.out.println("Geen ammo.");}
        }
        return doolhofMap;
    }   
    public Vak getVak(){
        return huidigeVak;
    }
    public boolean activeerOptimaleRoute() {
        if(helper.getAantal() > 0) {
            helper.gebruik();
            return true;
        }
        return false;
    }
}
