/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JComponent;

/**
 *
 * @author vfelsenaar
 */
public class HelperPad extends JComponent {
    ArrayList<Vak> doolhofMap = new ArrayList<Vak>();
    LinkedList<Integer> kortste_route = new LinkedList<Integer>();
    public HelperPad(LinkedList<Integer> kortste_route, ArrayList<Vak> doolhofMap)  {
        this.doolhofMap = doolhofMap;
        this.kortste_route = kortste_route;
    }
            
    
    public void paint(Graphics g) {
        
        for (int i = 0; i < kortste_route.size(); i++) {
            System.out.println(i);
        }
        
//        ListIterator<Vak> iterator = doolhofMap.listIterator();
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next().toString());
//    }
//
//        while(iterator.hasNext()) {
//            Vak vak = iterator.next();
//            Figuur figuur = vak.getFiguur();

// DEFAULT //           
//            g.setColor(figuur.getkleur());
//            // x en y as lijken omgedraaid te moeten...
//            g.fillRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels);
//            g.setColor(figuur.getkleur1());
//            //g.fillRect((vak.gety()*vak_size_pixels)+5, (vak.getx()*vak_size_pixels)+5, vak_size_pixels-10, vak_size_pixels-10);
//            //g.setColor(figuur.getkleur2());
//            //g.fillRect((vak.gety()*vak_size_pixels)+10, (vak.getx()*vak_size_pixels)+10, vak_size_pixels-20, vak_size_pixels-20);
//            int change = MAX_MAZE_SIZE/10;
//            int change2 = change*2;
//            g.fillRect((vak.gety()*vak_size_pixels)+change, (vak.getx()*vak_size_pixels)+change, vak_size_pixels-change*2, vak_size_pixels-change*2);
//            g.setColor(figuur.getkleur2());
//            g.fillRect((vak.gety()*vak_size_pixels)+change2, (vak.getx()*vak_size_pixels)+change2, vak_size_pixels-change2*2, vak_size_pixels-change2*2);

//        }
    }    
}
