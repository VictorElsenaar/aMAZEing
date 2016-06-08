package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Speler extends Figuur {
    private Bazooka bazooka;
    private Helper helper;
    private Cheater cheater;
    private Teleport teleport = null;
    private Vak huidigeVak;
    private int aantalStappen = 0;

    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Speler(int vak_size_pixels, String theme) {
        super(Color.BLUE, vak_size_pixels, theme);
        bazooka = new Bazooka(vak_size_pixels, theme);
        helper = new Helper(vak_size_pixels, theme);
        initialiseerImage("speler");
    }
    
    /**
     * 
     * @param richting = richting waarop de speler zijn actie uitvoert.
     * @param current_maze_size = maat van de map
     * @return de waarde van het aantal posities dat in de string van het level gesprongen moet worden om in die richting te lopen
     */
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
    
    /**
     * 
     * @param richting = richting waarop de speler wilt verplaatsen
     * @param doolhofMap = de map
     * @param current_maze_size = maat van de map
     * @param spelersVak = het spelersvak
     * @return het doolhofMap waarin de actie van de speler uitgevoerd is.
     */
    public ArrayList<Vak> move(String richting, ArrayList<Vak> doolhofMap, int current_maze_size, Vak spelersVak) {
        int interactie = 0;
        int position_change_amount = positionchange(richting, current_maze_size);
        int tempindex = doolhofMap.indexOf(spelersVak);
        Vak oudeVak = doolhofMap.get(tempindex);
        int new_position_index = (tempindex+position_change_amount);
        Vak nieuweVak = doolhofMap.get(new_position_index);
        
        if(!nieuweVak.isMuur(nieuweVak)) {
            // Oude vak speler ophalen
            Speler huidigeSpeler = (Speler) oudeVak.getFiguur();

            // Haal het panel op van het oude vak. Gooi de componenten weg die op dat panel staan.
            JPanel oudpanel = oudeVak.getPanel();
            oudpanel.removeAll();            
            
            // OUDE VAK LEEG MAKEN
            Figuur empty = new Leeg(vak_size_pixels, theme);
            
            if(teleport != null) {
                // we zijn dus net geteleport
                Teleport arrivedPortal = null;
                arrivedPortal = teleport.getOther();
                oudeVak.setFiguur(arrivedPortal);
                oudpanel.add(arrivedPortal);
                teleport = null;
            } else {
                oudeVak.setFiguur(empty);    
                oudpanel.add(empty);
            }
            doolhofMap.set(tempindex, oudeVak);

            addaantalStappen();
            if(nieuweVak.isBazooka(nieuweVak)) {
                interactie = 1;    
                bazooka.toevoegenAmmo();
                if(debug){System.out.println("Aantal raketten over van bazooka: " + bazooka.getAmmo());}
            }
            if(nieuweVak.isHelper(nieuweVak)) {
                interactie = 1;
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
                interactie = cheater.getWaarde();
                if(debug){System.out.println("Cheater opgepakt met waarde: " + cheater.getWaarde());}
            }
           // Teleport teleport1 = null;
            if(nieuweVak.isTeleport(nieuweVak)) {
                teleport = (Teleport)nieuweVak.getFiguur();
                new_position_index = teleport.getOther().getLocationIndex();
                nieuweVak = doolhofMap.get(new_position_index);
               
            }
            nieuweVak.setFiguur(huidigeSpeler);
            doolhofMap.set(new_position_index,nieuweVak);
            setVak(doolhofMap.get(new_position_index));
            // Haal het panel op van het nieuwe vak. Gooi de componenten weg die op dat panel staan en voeg object Speler toe op het panel.
            JPanel panel = huidigeVak.getPanel();
            panel.removeAll(); 
            panel.add(huidigeSpeler);
            panel.repaint();
            oudpanel.repaint();
            // teken de interactie indien aanwezig, indien je een item oppakt dat waarde heeft
            if (interactie != 0) {
                Runnable inter = new Interactie(interactie, huidigeVak);
                new Thread(inter).start();
                // Anders hangt hij heel even bij het eerste op te pakken item (bij een fresh start van het spel)
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            }            
        }
        return doolhofMap;
    }
    /**
     * 
     * @param richting = richting waarop de speler wilt schieten
     * @param doolhofMap = map 
     * @param current_maze_size = maat van de map
     * @param spelersVak = het spelersvak
     * @return 
     */
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
     * Wanneer een speler een stap maakt, wordt er een stap opgeteld voor de statistieken
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
