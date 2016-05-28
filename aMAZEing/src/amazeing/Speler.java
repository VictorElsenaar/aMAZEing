package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author vic
 */
public class Speler extends Figuur {

    private Bazooka bazooka = new Bazooka(vak_size_pixels, theme);
    private Helper helper = new Helper(vak_size_pixels, theme);
    private Vak huidigeVak;
    
    private BufferedImage spelerImage;

    // Constructor
    public Speler(int vak_size_pixels, String theme) {
        super(Color.BLUE); //Color(0,0,255)
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
        setVakSizePixels(vak_size_pixels);
        setTheme(theme);
        InitialiseerImage();
    }
    
    public void paint(Graphics g) {
        g.drawImage(spelerImage.getScaledInstance(vak_size_pixels,vak_size_pixels,0), 0, 0, null);
    }
    
    public void InitialiseerImage() {
        try {
            spelerImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\speler.jpg"));
        }
        catch (Exception e) {
        }
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
            Figuur empty = new Leeg(vak_size_pixels, theme);
            oudeVak.setFiguur(empty);
            doolhofMap.set(tempindex, oudeVak);
            
            JPanel oudpanel = oudeVak.getPanel();
            oudpanel.removeAll();
            oudpanel.add(empty);

            if(nieuweVak.isBazooka(nieuweVak)) {
                bazooka.toevoegenAmmo();
                System.out.println("Aantal raketten over van bazooka: " + bazooka.getAmmo());
            }
            if(nieuweVak.isHelper(nieuweVak)) {
                helper.toevoegenAantal();
            }
            nieuweVak.setFiguur(huidigeSpeler);
            doolhofMap.set(tempindex+position_change_amount,nieuweVak);
            huidigeVak = doolhofMap.get(tempindex+position_change_amount);
            
            //spelersVak = huidigeSpeler.getVak();
        
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
