/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author vic
 */
public class Interactie extends JComponent implements Runnable {
    private int waarde;
    private Vak huidigeVak;
    public Interactie(int waarde, Vak huidigeVak) {
        setLayout(null);
        setSize(Level.global_vak_size_pixels, Level.global_vak_size_pixels); // maakt gebruik van public static global_vak_size_pixels
        this.waarde = waarde;
        this.huidigeVak = huidigeVak;
    }

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
