package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Bazooka extends Figuur{    
    private int ammo;
    
    /**
     * Instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Bazooka(int vak_size_pixels, String theme) {
        super(Color.CYAN, vak_size_pixels, theme);
        ammo = 0;
        if(debug) {ammo = 99;}
        initialiseerImage("bazooka");
    }
    public int getAmmo() {
        return ammo;
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    /**
     * Bazooka wordt opgepakt dus wordt ammo met 1 verhoogd.
     */
    public void toevoegenAmmo() {
        ammo++;
    }
    
    public ArrayList<Vak> fire(String richting, ArrayList<Vak> doolhofMap, Vak spelersVak, int position_change_amount) {
        int currentLocationIndex = doolhofMap.indexOf(spelersVak);
        Vak schietvak = doolhofMap.get(currentLocationIndex+position_change_amount);
        int i = 1;
        while(!schietvak.isMuur(schietvak)) {
            JPanel panel = schietvak.getPanel();
            Bom bom = new Bom();
            panel.add(bom);
            panel.setComponentZOrder(bom, 0);
            panel.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bazooka.class.getName()).log(Level.SEVERE, null, ex);
            }
            schietvak = doolhofMap.get(currentLocationIndex+(position_change_amount*i));
            i++;
            panel.remove(bom);
            panel.repaint();
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
                Figuur empty = new Leeg(vak_size_pixels, theme);
                schietvak.setFiguur(empty);
                JPanel panel = schietvak.getPanel();
                Explosie explosie = new Explosie();
                panel.removeAll();
                panel.add(explosie);
                revalidate();
                panel.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bazooka.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                panel.removeAll();
                panel.add(empty);
                revalidate();
                panel.repaint();
            }
        }
        ammo --;
        if (debug){System.out.println("Ammo nog over: " + ammo);}
        return doolhofMap;
    }
}
