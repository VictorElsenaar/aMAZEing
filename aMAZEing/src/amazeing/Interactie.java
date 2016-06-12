package amazeing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Class Interactie zorgt ervoor dat de speler een visuele interactie krijgt bij het oppakken van een item.
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Interactie extends JComponent implements Runnable {
    private int waarde;
    private Vak huidigeVak;
    
    /**
     * Nieuw instantie van dit object
     * @param waarde = waarde dat een interactie heeft
     * @param huidigeVak = vak waar interactie plaatsvindt
     */
    public Interactie(int waarde, Vak huidigeVak) {
        setLayout(null);
        setSize(Level.global_vak_size_pixels, Level.global_vak_size_pixels); // maakt gebruik van public static global_vak_size_pixels
        this.waarde = waarde;
        this.huidigeVak = huidigeVak;
    }
    /**
     * Als waarde == 1 dan een Groene textbox (dus oppakken item dat +1 doet) 
     * Anders een rode (dus cheater, min stappen)
     * @param g 
     */
    public void paint(Graphics g) {
        if(waarde == 1){
            g.setColor(Color.GREEN); 
            g.setFont(new Font("Courier", Font.BOLD, Level.global_vak_size_pixels/2)); 
            g.drawString("+1", 1, Level.global_vak_size_pixels/2);
        }else{
            g.setColor(Color.RED);
            g.setFont(new Font("Courier", Font.BOLD, Level.global_vak_size_pixels/2)); 
            g.drawString("-" + waarde, 1, Level.global_vak_size_pixels/2);
        }      
    }
    
    public void run() {
        JPanel panel = huidigeVak.getPanel();
        panel.add(this);
        panel.setComponentZOrder(this, 0);
        panel.repaint();    
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bazooka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        panel.remove(this);
        panel.repaint();
    }
    
}
