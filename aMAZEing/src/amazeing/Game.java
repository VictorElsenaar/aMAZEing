package amazeing;
import static amazeing.AMAZEing.debug;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author Kahoo
 */
public class Game extends JFrame{
//    private final int VAKGROOTTE = 25;
//    private final int AANTALVAKKENBREEDTE = 10;
//    private final int AANTALVAKKENHOOGTE = 10;
//    
//    
//    
//    private final int ROWS = 10;
//    private final int COLUMNS = 10;
//    
//    private Map<Vak,Figuur> map;
    boolean fireing = false;
    private Level level;
    public Game() {
       level = new Level();
       add(level);
       
        addKeyListener(new KeyListener() { 
            public void keyPressed(KeyEvent e) { } 
            @Override
            public void keyReleased(KeyEvent e) { 
                if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                   if(debug){System.out.println("DOWN");}
                   if(fireing){
                       if(debug) {System.out.println("FIRE DOWN"); }
                       //level.fire_down();
                       level.fire("down");
                       fireing = false;
                   } else { // move character
                       //level.move_down();
                       level.move("down");

                   }
                } 
                if (e.getKeyCode() == KeyEvent.VK_UP ) {
                   if(debug){System.out.println("UP");}
                   if(fireing){
                       if(debug) {System.out.println("FIRE UP"); }
                      // level.fire_up();  
                       level.fire("up");
                       fireing = false;
                   } else { // move character                   
                       level.move("up");
                   }
                } 
                if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                   if(debug){System.out.println("RIGHT");}
                    if(fireing){
                       if(debug) {System.out.println("FIRE RIGHT"); }
                       level.fire("right");  
                       fireing = false;
                   } else { // move character
                        level.move("right");
                   }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                   if(debug){System.out.println("LEFT");}
                   if(fireing){
                       if(debug) {System.out.println("FIRE LEFT"); }
                       //level.fire_left();
                       level.fire("left");
                       fireing = false;
                   } else { // move character                   
                       level.move("left");
                   }
                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    if(debug){System.out.println("(F)IRE - pick a direction");}
                    fireing = true;
                }
            } 
            public void keyTyped(KeyEvent e) { } 
        }); 
       
      // setLevel(levelOne());
      // if(debug){readLevel();} // controleer het level
    }
    
//    public void tekenLevel() {
//        Muur borderMuur = new Muur();
//        Figuur empty = new Empty();
//        borderMuur.setBorderMuur(true);
//        map = new HashMap<>();
//        for(int x = 0; x < AANTALVAKKENBREEDTE; x++) {
//            for(int y = 0; y < AANTALVAKKENHOOGTE; y++) {
//                if (x == 0 || x == (AANTALVAKKENBREEDTE-1) || y == 0 || y == (AANTALVAKKENHOOGTE-1)) {
//                    map.put(new Vak(x,y),borderMuur);
//                } else {
//                    map.put(new Vak(x,y),empty);
//                }
//            }
//        }
//    }
/*    public void setLevel(String level) {
        System.out.println(level);

        Muur muur = new Muur();
        Figuur empty = new Empty();
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
        for (int i = 0; i < levelsize; i+=VAKGROOTTE) {
            for (int j = 0; j < levelsize; j+=VAKGROOTTE) {
              g.drawLine(i, j, levelsize, j); // de grid tekenen
              g.drawLine(i, j, i, levelsize); // de grid tekenen
            }
        }
        
        Set<Vak> keySet = map.keySet();
        for (Vak key : keySet)
        {
            Figuur value = map.get(key);
            if (value.getNaam().equals("muur")) { // als object een muur is
                
                g.fillRect((key.getXAs()*VAKGROOTTE), (key.getYAs()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE); // vul de vakken zwart
            }
        }
    }
        
    
    public String levelOne() {
      return  "1111111111"
            + "1010100001"
            + "1010101011"
            + "1010101001"
            + "1010001101"
            + "1010110001"
            + "1010100111"
            + "1010101101"
            + "1000100001"
            + "1111111111";
    }    */
}
