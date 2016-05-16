/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import static amazeing.AMAZEing.debug;
//import static amazeing.AMAZEing.speler;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;

/**
 *
 * @author vic
 */
public class Level extends JComponent{
    private final int LEVEL_FRAME_SIZE = 500;
    private final int VAKGROOTTE = 25;
    //private final int AANTALVAKKENBREEDTE = 10;
    //private final int AANTALVAKKENHOOGTE = 10;
    
    
    
    private final int ROWS = 10;
    private final int COLUMNS = 10;
    
    private Map<Vak,Figuur> map;
    private Speler speler;
    
    public Level() {
        setLevel(levelOne());
        if(debug){readLevel();} // controleer het level
           
    }
    
    public void move_down() {
        System.out.println("blaaaaaaaaaaaaaaaaaaaaa");
        // in plaatsen van speler op map, bewaar key naar value in speler (hashcode key)
        
        
        
        // Haal op basis van key de values op
        // verwijder
        // maak nieuwe aan van nieuwe positie
        System.out.println("voor vak");
        System.out.println(speler.getx());
        Vak vak = new Vak(speler.getVak().getXAs(),speler.getVak().getYAs());
        System.out.println("na new vak");
        System.out.println("voor remove");
        map.remove(vak);
        System.out.println("na remove");
        map.put(new Vak(speler.getx()+1,speler.gety()),speler);
        
        //readLevel();
    }
    
    public void setLevel(String level) {
        System.out.println(level);

        Muur muur = new Muur();
        Figuur empty = new Empty();
        speler = new Speler();
        map = new HashMap<>();
        
        int counter = 0;
        for (int x = 0; x < ROWS ; x++) {
            if(debug){System.out.println("rows " + x);}
            for(int y = 0; y < COLUMNS ; y++) {
                if(debug){System.out.println("columns " + y);}
                String typeOnPosition = level.substring(counter, counter+1);
                if(debug){System.out.println(typeOnPosition);}
                if(Integer.parseInt(typeOnPosition) == 1) {
                     map.put(new Vak(y,x),muur);
                   // map[x][y] = new Tile(new Figure("Muur"));
                } else if (Integer.parseInt(typeOnPosition) == 2) {
                    map.put(new Vak(y,x),speler);
                    speler.setx(y);
                    speler.sety(x);
                    speler.setVak(new Vak(y,x));
                    System.out.println("Victor: " + speler.getx());
                   // map[x][y] = new Tile(new Figure("Speler"));
                } else
                {
                    map.put(new Vak(y,x),empty);
                   // map[x][y] = new Tile(new Figure("Leeg"));
                }
                counter++;
            }
            
        }
    }

    public void readLevel() {
//        Set<Vak> keySet = map.keySet();
//        for (Vak key : keySet)
//        {
//            Figuur value = map.get(key);
//            System.out.println(key.getCord()+ "->" + value.getNaam()); // coordinaten + object output in console
//        }
        for (Map.Entry<Vak,Figuur> entry : map.entrySet()) {
            System.out.println(entry.getKey().getCord() + "/" + entry.getValue().getNaam());
        }
    }
    
    public void paint(Graphics g) {
//        for (int i = 0; i < LEVEL_FRAME_SIZE; i+=VAKGROOTTE) {
//            for (int j = 0; j < LEVEL_FRAME_SIZE; j+=VAKGROOTTE) {
//              g.drawLine(i, j, LEVEL_FRAME_SIZE, j); // de grid tekenen
//              g.drawLine(i, j, i, LEVEL_FRAME_SIZE); // de grid tekenen
//            }
//        }
        
        Set<Vak> keySet = map.keySet();
        for (Vak key : keySet)
        {
            Figuur value = map.get(key);
            if (value.getNaam().equals("muur")) { // als object een muur is
                g.setColor(java.awt.Color.black);
                g.fillRect((key.getXAs()*VAKGROOTTE), (key.getYAs()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE); // vul de vakken zwart
            } else if (value.getNaam().equals("speler")) {
                g.setColor(java.awt.Color.red);
                g.fillRect((key.getXAs()*VAKGROOTTE), (key.getYAs()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE);
            }
        }
    }
        
    
    public String levelOne() {
      return  "1111111111"
            + "1210100001"
            + "1010101011"
            + "1010101001"
            + "1010001101"
            + "1010110001"
            + "1010100111"
            + "1010101101"
            + "1000100001"
            + "1111111111";
    }   
}
