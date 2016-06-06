package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Speler extends Figuur {
    private Bazooka bazooka;
    private Helper helper;
    private Cheater cheater;
    private Vak huidigeVak;
    private int aantalStappen = 0;

    // Constructor
    public Speler(int vak_size_pixels, String theme) {
        super(Color.BLUE, vak_size_pixels, theme);
        bazooka = new Bazooka(vak_size_pixels, theme);
        helper = new Helper(vak_size_pixels, theme);
        initialiseerImage("speler");
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
        int tempindex = doolhofMap.indexOf(spelersVak);
        Vak oudeVak = doolhofMap.get(tempindex);
        Vak nieuweVak = doolhofMap.get(tempindex+position_change_amount);
        
        if(!nieuweVak.isMuur(nieuweVak)) {
            // Oude vak speler ophalen
            Speler huidigeSpeler = (Speler) oudeVak.getFiguur();

            // OUDE VAK LEEG MAKEN
            Figuur empty = new Leeg(vak_size_pixels, theme);
            oudeVak.setFiguur(empty);
            doolhofMap.set(tempindex, oudeVak);
            
            // Haal het panel op van het oude vak. Gooi de componenten weg die op dat panel staan en voeg object Leeg toe op het panel.
            JPanel oudpanel = oudeVak.getPanel();
            oudpanel.removeAll();
            oudpanel.add(empty);

            addaantalStappen();
            if(nieuweVak.isBazooka(nieuweVak)) {
                bazooka.toevoegenAmmo();
                if(debug){System.out.println("Aantal raketten over van bazooka: " + bazooka.getAmmo());}
            }
            if(nieuweVak.isHelper(nieuweVak)) {
                helper.toevoegenAantal();
                if(debug){System.out.println("Aantal helper over: " + helper.getAantal());}
            }
            if(nieuweVak.isCheater(nieuweVak)){
                cheater = (Cheater)nieuweVak.getFiguur();
                if(aantalStappen-cheater.getWaarde() < 0) {
                    aantalStappen = 0;
                } else {
                    aantalStappen -= cheater.getWaarde();
                }
                if(debug){System.out.println("Cheater opgepakt met waarde: " + cheater.getWaarde());}
            }
            nieuweVak.setFiguur(huidigeSpeler);
            doolhofMap.set(tempindex+position_change_amount,nieuweVak);
            setVak(doolhofMap.get(tempindex+position_change_amount));
            
            // Haal het panel op van het nieuwe vak. Gooi de componenten weg die op dat panel staan en voeg object Speler toe op het panel.
            JPanel panel = huidigeVak.getPanel();
            panel.removeAll();
            panel.add(huidigeSpeler);
            revalidate();
            panel.repaint();
            oudpanel.repaint();
        }
        return doolhofMap;
    }
    public ArrayList<Vak> fire(String richting, ArrayList<Vak> doolhofMap, int current_maze_size, Vak spelersVak) {
        int position_change_amount = positionchange(richting, current_maze_size);
        if (bazooka.getAmmo() > 0) {
            this.bazooka.fire(richting, doolhofMap, spelersVak, position_change_amount);
        } else {
            if (debug) {System.out.println("Geen ammo.");}
        }
        return doolhofMap;
    }   
    public void setVak(Vak vak) {
        this.huidigeVak = vak;
    }
    public Vak getVak(){
        return huidigeVak;
    }
    /**
     * Checkt of je de helper kan gebruiken. 
     * @return true als aantal van helper > 0 is, anders return false.
     */
    public boolean activeerOptimaleRoute() {
        if(helper.getAantal() > 0) {
            helper.gebruik();
            return true;
        }
        return false;
    }
    public int getaantalStappen() {
        return this.aantalStappen;
    }
    /**
     * Wanneer een speler een stap maakt, wordt er een stap opgeteld. 
     */
    public void addaantalStappen() {
        this.aantalStappen++;
    }
    public int getKogels() {
        return this.bazooka.getAmmo();
    }
    public int getHelper() {
        return this.helper.getAantal();
    }
}
