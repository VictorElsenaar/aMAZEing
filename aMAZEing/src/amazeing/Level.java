package amazeing;

import static amazeing.AMAZEing.debug;
//import static amazeing.AMAZEing.speler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author vic
 */
public class Level extends JComponent{
    private final int LEVEL_FRAME_SIZE = 500;
    
    /**
     * maximaal aantal vakken op 1 lijn. (altijd vierkant) Max aantal blokken totaal zou zijn MAX_MAZE_SIZE * MAX_MAZE_SIZE
     */
    private final int MAX_MAZE_SIZE = 50; 
    
    // Bij het inladen van het level wordt deze maat bewaard van de huidige maze.
    private int current_maze_size;
    // en de maat in pixels van een vak
    private int vak_size_pixels;
    
    private LinkedList<Vak> doolhofMap;
    
    private Vak spelersVak;
    private Vak vriendVak;
    
    private String currentLevel;
        
    public Level() {
        setLevel(levelOne());
        if(debug){readLevel();} // controleer het level        
    }
    
    public void action(String richting, String type) {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();   
        switch(type) {
            case "move":
                doolhofMap = huidigeSpeler.move(richting, doolhofMap, current_maze_size, spelersVak );
                break;
            case "fire":
                doolhofMap = huidigeSpeler.fire(richting, doolhofMap, current_maze_size, spelersVak );
                break;
            default:
                break;
        }
        revalidate();
        repaint();
        spelersVak = huidigeSpeler.getVak();
        System.out.println(spelersVak.toString());
        System.out.println(vriendVak.toString());
//        
//        ListIterator<Vak> iterator = doolhofMap.listIterator();
//        while(iterator.hasNext()) {
//            Vak vak = iterator.next();
//            Figuur figuur = vak.getFiguur();
//                        if(figuur instanceof Speler){
//                // dan het vakje van de speler opslaan globaal, zodat we weten waar de speler is.
//                spelersVak = vak;
//            }
//        }
        
    }
    public void readLevel() {
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            System.out.println(vak.toString());
        }
        
    }
    
    public void paint(Graphics g) {
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            Figuur figuur = vak.getFiguur();
// TESTING
//            Graphics2D g2d = (Graphics2D) g;
//            Color color1 = figuur.getkleur();
//            Color color2 = color1.brighter();
//            color2 = color2.brighter();
//            GradientPaint gp = new GradientPaint(
//                    0, 0, color1,
//                    0, (vak.getx()*vak_size_pixels), color2);
//            g2d.setPaint(gp);
//            g2d.fillRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels);
// TESTING  
            
// DEFAULT //           
            g.setColor(figuur.getkleur());
            // x en y as lijken omgedraaid te moeten...
            g.fillRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels);
            g.setColor(figuur.getkleur1());
            g.fillRect((vak.gety()*vak_size_pixels)+5, (vak.getx()*vak_size_pixels)+5, vak_size_pixels-10, vak_size_pixels-10);
            g.setColor(figuur.getkleur2());
            g.fillRect((vak.gety()*vak_size_pixels)+10, (vak.getx()*vak_size_pixels)+10, vak_size_pixels-20, vak_size_pixels-20);
// DEFAULT //
            
            
            // Temp uitvogel ding
////            g.setColor(Color.GRAY);
////            g.fillRect((vak.gety()*VAKGROOTTE)+2, (vak.getx()*VAKGROOTTE)+2, VAKGROOTTE-4, VAKGROOTTE-4);
////            g.setColor(Color.LIGHT_GRAY);
////            g.fillRect((vak.gety()*VAKGROOTTE)+4, (vak.getx()*VAKGROOTTE)+4, VAKGROOTTE-8, VAKGROOTTE-8);
            
            if(figuur instanceof Bazooka) {
                BufferedImage image; 
                try {
                    image = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\bazooka.jpeg"));
                    g.drawImage(image, (vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), null);
                }
                catch (Exception e) {

                }
            }
            // Speler locatie globaal opslaan
            if(figuur instanceof Speler){
                // dan het vakje van de speler opslaan globaal, zodat we weten waar de speler is.
                spelersVak = vak;
            }
        }
    }
    public LinkedList<Vak> getcurrentMap() {
        return doolhofMap;
    }
    public int getMazeSize() {
        return current_maze_size;
    }
    public Vak getSpelersVak() {
        return spelersVak;
    }
    public Vak getVriendVak() {
        return vriendVak;
    }
    public String getCurrentLevel() {
        return currentLevel;
    }  
    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }
    public void setLevel(String level) {
        setCurrentLevel(level);
        // Bepaal de breedte en hoogte
        for (int i = 1; i < MAX_MAZE_SIZE; i++) {
            if((level.length() / i) == i){
                current_maze_size = i;
            }
        }
        // Bepaal aantal pixels voor de map, zodat hij netjes het frame vult.
        vak_size_pixels = LEVEL_FRAME_SIZE / current_maze_size;
        
        System.out.println(level);
        

        Muur muur = new Muur();
        Muur buitenmuur = new Muur(true);
        Figuur empty = new Leeg();
        Speler speler = new Speler();
        Vriend vriend = new Vriend();
        Bazooka bazooka = new Bazooka();
        
        doolhofMap = new LinkedList<>();
         
        int counter = 0;
        for (int x = 0; x < current_maze_size ; x++) {
            if(debug){System.out.println("rows " + x);}
            for(int y = 0; y < current_maze_size ; y++) {
                Vak vak;
                if(debug){System.out.println("columns " + y);}
                String typeOnPosition = level.substring(counter, counter+1);
                if(debug){System.out.println(typeOnPosition);}
                // Als het een 1 is, dan een buitenmuur plaatsen
                if(Integer.parseInt(typeOnPosition) == 1) {
                    vak = new Vak(x,y,buitenmuur);
                // Als het een 2 is dan een binnenmuur plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 2) {
                    vak = new Vak(x,y,muur);
                // Als het een 3 is dan een speler plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 3) {
                    vak = new Vak(x,y,speler);
                // Als het een 4 is dan een vriend plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 4) {
                    vak = new Vak(x,y,vriend);
                    vriendVak = vak;
                // Als het een 5 is dan een bazooka plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 5) {
                    vak = new Vak(x,y,bazooka);                    
                } else // ANDERS is het een leeg vak
                {
                    vak = new Vak(x,y,empty);
                }
                doolhofMap.add(vak); 
                counter++;
            }
            
        }
    }    
    public String levelOne() {
      return  "1111111111"
            + "1320200001"
            + "1020202021"
            + "1020202001"
            + "1020052201"
            + "1020220001"
            + "1020200221"
            + "1020202241"
            + "1000200001"
            + "1111111111";
    }   
    public String levelTwo() {
      return  "1111111111"
            + "1230000001"
            + "1020202201"
            + "1020200001"
            + "1020222221"
            + "1000220001"
            + "1022000201"
            + "1020022201"
            + "1000224001"
            + "1111111111";
    }
    public String levelThree() {
        return "11111111111111111111" 
            +  "10000020002000500031" 
            +  "10222020202022222221"
            +  "10200020202020002401"                
            +  "10202220000020202201"                
            +  "10200000222220202001"                
            +  "10222222200000200021"                
            +  "10202000202222222221"                
            +  "10202020200000000001"                
            +  "10250020202222222201"
            +  "10222220200002000201"
            +  "10000000202202020201"
            +  "10222222252200020201"                
            +  "10000000222222220201"                
            +  "12022220200022220201"                
            +  "10002000202000000201"                
            +  "10202020202022220201"                
            +  "10202020202000200201"                
            +  "10200020002020000001"                
            +  "11111111111111111111";
    }
}
/* Usefull old codes

ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            if(vak.equals(spelersVak)) {
                Figuur figuur = vak.getFiguur();
                if(debug){System.out.println(figuur.getNaam());}
                
                // nu zijn we in de list beland op de spelerspositie.
                // maak het vak leeg want hij loopt naar beneden @@@@ CONTROLE NOG INBOUWEN OF HET UBERHAUPT WEL KAN :-)
                tempindex = doolhofMap.indexOf(spelersVak);
                Figuur empty = new Leeg();
                vak.setFiguur(empty);
                iterator.set(vak);
                               
                Vak nieuweVak = doolhofMap.get(tempindex+MAZESIZE);
                Speler speler = new Speler();
                nieuweVak.setFiguur(speler);

                doolhofMap.set(tempindex+MAZESIZE, nieuweVak);
               
                readLevel();
                revalidate();
                repaint();
                // Naar links = iterator.previous;
                // naar rechts = iterator.next;
                // naar beneden = iterator+10;
                // naar boven = iterator-10;
                break;
            }
        }


*/