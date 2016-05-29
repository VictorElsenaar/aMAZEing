package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Victor
 */
public class Bazooka extends Figuur{
    
    private int ammo;
    
    
    private BufferedImage bazookaImage;
    
    // Constructor
    public Bazooka(int vak_size_pixels, String theme) {
        super(Color.CYAN); //Color(0,255,255)
        ammo = 0;
        if(debug) {ammo = 99;}
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        this.vak_size_pixels = vak_size_pixels;
        setVakSizePixels(vak_size_pixels);
        this.theme = theme;
        InitialiseerImage();
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
            
            JPanel panel = schietvak.getPanel();
            Bom bom = new Bom();
            
            panel.add(bom);
            panel.setComponentZOrder(bom, 0);
            revalidate();
            panel.repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bazooka.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            schietvak = doolhofMap.get(currentLocationIndex+(position_change_amount*i));
            i++;
            panel.remove(bom);
            revalidate();
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
                    Thread.sleep(200);
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
    
    public void paint(Graphics g) {
        g.drawImage(bazookaImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null); 
    }
    
    public void InitialiseerImage() {
        try {
            bazookaImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\bazooka.jpg")); 
        }
        catch (Exception e) {
        }
    }
}
