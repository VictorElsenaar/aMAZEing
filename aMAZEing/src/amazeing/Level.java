package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
//import static amazeing.AMAZEing.speler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
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
    
    private ArrayList<Vak> doolhofMap;
    
    private Vak spelersVak;
    private Vak vriendVak;
    
    private String currentLevel;
    
    private boolean toonOptimaleRoute = false;
    
    public Level() {
        setLevel(levelOne());
        if(debug){readLevel();} // controleer het level    
//        System.out.println("spelersVak " + spelersVak.toString());
//        System.out.println("vriendVak " + vriendVak.toString());
//        System.out.println("current_maze_size " + current_maze_size);
//        System.out.println("doolhofMap " + doolhofMap.toString());
      //OptimaleRoute.vindRoute(doolhofMap, current_maze_size, spelersVak, vriendVak);
    }
    
    public void action(String direction, String type) {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();  
        switch(type) {
            case "move":
                doolhofMap = huidigeSpeler.move(direction, doolhofMap, current_maze_size, spelersVak );
                break;
            case "fire":
                doolhofMap = huidigeSpeler.fire(direction, doolhofMap, current_maze_size, spelersVak );
                break;
            case "optimal_route":
                
                toonOptimaleRoute = huidigeSpeler.activeerOptimaleRoute();
                break;
            default:
                break;
        }
        revalidate();
        repaint();
        spelersVak = huidigeSpeler.getVak();
        if(debug){System.out.println(spelersVak.toString());}
        if(debug){System.out.println(vriendVak.toString());}
        
    }

    public void readLevel() {
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            if(debug){System.out.println(vak.toString());}
        }
        
    }
    
    public void paint(Graphics g) {
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        BufferedImage image;
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
            
            if (figuur instanceof Muur) {
                Muur muur = (Muur)vak.getFiguur();
                if (muur.getBorderMuur()) {
                    g.setColor(muur.getkleur());
                    g.drawRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels); // vierkant
                    g.drawLine((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels)+vak_size_pixels, (vak.gety()*vak_size_pixels)+vak_size_pixels, (vak.getx()*vak_size_pixels)); // derde (middelste) diagonale lijn
                    g.drawLine((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels)+(vak_size_pixels/3), (vak.gety()*vak_size_pixels)+(vak_size_pixels/3), (vak.getx()*vak_size_pixels)); // eerste diagonale lijn
                    g.drawLine((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels)+(vak_size_pixels/3)*2, (vak.gety()*vak_size_pixels)+(vak_size_pixels/3)*2, (vak.getx()*vak_size_pixels)); // tweede diagonale lijn
                    g.drawLine((vak.gety()*vak_size_pixels)+(vak_size_pixels/3), (vak.getx()*vak_size_pixels)+vak_size_pixels, (vak.gety()*vak_size_pixels)+vak_size_pixels, (vak.getx()*vak_size_pixels)+(vak_size_pixels/3)); // vierde diagonale lijn
                    g.drawLine((vak.gety()*vak_size_pixels)+(vak_size_pixels/3)*2, (vak.getx()*vak_size_pixels)+vak_size_pixels, (vak.gety()*vak_size_pixels)+vak_size_pixels, (vak.getx()*vak_size_pixels)+(vak_size_pixels/3)*2); // vijfde diagonale lijn
                } else {
                    g.setColor(muur.getkleur());
                    g.drawRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels); // vierkant
                    g.drawLine((vak.gety()*vak_size_pixels)+(vak_size_pixels/3), (vak.getx()*vak_size_pixels), (vak.gety()*vak_size_pixels)+(vak_size_pixels/3), (vak.getx()*vak_size_pixels)+vak_size_pixels); // eerste verticale lijn
                    //g.drawLine((vak.gety()*vak_size_pixels)+(vak_size_pixels/2), (vak.getx()*vak_size_pixels), (vak.gety()*vak_size_pixels)+(vak_size_pixels/2), (vak.getx()*vak_size_pixels)+vak_size_pixels); // tweede (middelste) verticale lijn
                    g.drawLine((vak.gety()*vak_size_pixels)+(vak_size_pixels/3)*2, (vak.getx()*vak_size_pixels), (vak.gety()*vak_size_pixels)+(vak_size_pixels/3)*2, (vak.getx()*vak_size_pixels)+vak_size_pixels); // derde verticale lijn
                    g.drawLine((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels)+(vak_size_pixels/3), (vak.gety()*vak_size_pixels)+vak_size_pixels, (vak.getx()*vak_size_pixels)+(vak_size_pixels/3)); // eerste horizontale lijn
                    //g.drawLine((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels)+(vak_size_pixels/2), (vak.gety()*vak_size_pixels)+vak_size_pixels, (vak.getx()*vak_size_pixels)+(vak_size_pixels/2)); // tweede (middelste) horizontale lijn
                    g.drawLine((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels)+(vak_size_pixels/3)*2, (vak.gety()*vak_size_pixels)+vak_size_pixels, (vak.getx()*vak_size_pixels)+(vak_size_pixels/3)*2); // derde horizontale lijn
                }
            }
            if(figuur instanceof Helper){
                g.setColor(figuur.getkleur());
                 g.fillRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels);
            }
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
// DEFAULT //
            
            
            // Temp uitvogel ding
////            g.setColor(Color.GRAY);
////            g.fillRect((vak.gety()*VAKGROOTTE)+2, (vak.getx()*VAKGROOTTE)+2, VAKGROOTTE-4, VAKGROOTTE-4);
////            g.setColor(Color.LIGHT_GRAY);
////            g.fillRect((vak.gety()*VAKGROOTTE)+4, (vak.getx()*VAKGROOTTE)+4, VAKGROOTTE-8, VAKGROOTTE-8);
            
            if(figuur instanceof Bazooka) {
                try {
                    image = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\bazooka.jpeg"));
                    g.drawImage(image.getScaledInstance(vak_size_pixels,vak_size_pixels,0), (vak.gety()*vak_size_pixels)+1, (vak.getx()*vak_size_pixels)+1, null); // +1 om overlapping te voorkomen
                }
                catch (Exception e) {
                }
            }
            // Speler locatie globaal opslaan
            if(figuur instanceof Speler){
                // dan het vakje van de speler opslaan globaal, zodat we weten waar de speler is.
                spelersVak = vak;
                try {
                    image = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\speler.jpg"));
                    g.drawImage(image.getScaledInstance(vak_size_pixels,vak_size_pixels,0), (vak.gety()*vak_size_pixels)+1, (vak.getx()*vak_size_pixels)+1, null); // +1 om overlapping te voorkomen
                }
                catch (Exception e) {
                }
            }
            if (figuur instanceof Vriend) {
                try {
                    image = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\vriend.jpg"));
                    g.drawImage(image.getScaledInstance(vak_size_pixels,vak_size_pixels,0), (vak.gety()*vak_size_pixels)+1, (vak.getx()*vak_size_pixels)+1, null); // +1 om overlapping te voorkomen
                }
                catch (Exception e) {
                }
            }
        }
        // Indien speler de optimale route te zien moet krijgen dan onderstaande uitvoeren.
        if(toonOptimaleRoute){      
            LinkedList<Integer> kortste_route = new LinkedList<Integer>();
            kortste_route = OptimaleRoute.vindRoute(doolhofMap, current_maze_size, spelersVak, vriendVak);
            g.setColor(new Color(159,255,255,124));
            for (int i = 0; i < kortste_route.size(); i++) {
                //System.out.println(i);
                //System.out.println("#:" + kortste_route.get(i));
                //System.out.println("#:" + doolhofMap.get(kortste_route.get(i)));
                Vak vak = doolhofMap.get(kortste_route.get(i));
                g.fillRect((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels-5, vak_size_pixels-5);
            }
            kortste_route.clear();
            toonOptimaleRoute = false;
        }
    }
    public ArrayList<Vak> getcurrentMap() {
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
        
        if(debug){System.out.println(level);}
        

        Muur muur = new Muur();
        Muur buitenmuur = new Muur(true);
        Figuur empty = new Leeg();
        Speler speler = new Speler();
        Vriend vriend = new Vriend();
        Bazooka bazooka = new Bazooka();
        Helper helper = new Helper();
        
        doolhofMap = new ArrayList<Vak>();
         
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
                    spelersVak = vak;
                // Als het een 4 is dan een vriend plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 4) {
                    vak = new Vak(x,y,vriend);
                    vriendVak = vak;
                // Als het een 5 is dan een bazooka plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 5) {
                    vak = new Vak(x,y,bazooka);                    
                } else if (Integer.parseInt(typeOnPosition) == 6) {
                    vak = new Vak(x,y,helper);                    
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
            + "1026202021"
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
            + "1020260001"
            + "1020222221"
            + "1000220001"
            + "1022000201"
            + "1020022201"
            + "1000224001"
            + "1111111111";
    }
    public String levelThree() {
        return "11111111111111111111" 
            +  "10000020002000000031" 
            +  "10222020262022222221"
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
            +  "10202020202000220201"                
            +  "16200020002020000001"                
            +  "11111111111111111111";
    }
    public String levelFour() {
        return "1111111111111111111111111111111111111111"
            +  "1000002000200002000000000224222222000001"
            +  "1020202020202222022222020020000000022201"
            +  "1023202020000000026000020222222222020201"
            +  "1022202022222222222222020020000000020201" 
            +  "1020202000000000000000022220222202220201"
            +  "1020202022222222002220002020000202000201"
            +  "1020200000000002000022202020222200020001"
            +  "1020222222202222222000200020200002222221"   
            +  "1020000000200000002020222220222022200001"
            +  "1022222220202222202020200020002000220201"
            +  "1000000020202000202020202020202220000201"
            +  "1220222020202020200020202020202022222201" 
            +  "1520202020202220222220202020202020000001"
            +  "1020202000200000200020202025200020222221"
            +  "1020202222222220202020202022222020000001"
            +  "1020200000000020202020002020002022222021"
            +  "1020202222222020002022222020202020002021"
            +  "1020202000002020222000200020202000202001"
            +  "1020202022202020202020202220202222202201"
            +  "1020202025202020202020200000222226200201" 
            +  "1020202020202020000020222222200000220201"
            +  "1020202000202020222020000000002222200201"
            +  "1020202222202020200022222222222020002201"
            +  "1020200000002020202020002000200022200201"
            +  "1020222222222020202020200020002002222201"
            +  "1020000000000020002020222222222200000001" 
            +  "1020222222222220200020000000000022222221"
            +  "1020200020002000222222222222222000200001"
            +  "1000202000200020002000000000002220202201"
            +  "1222202222222222202022222222202000202001"
            +  "1600002000200000202020000000202022202021"
            +  "1022222020202220202020222220202000002001"
            +  "1000000020202000202020200020202222222201"
            +  "1022222020002020202020202020202000200001" 
            +  "1020002022202020200020202020002020202221"
            +  "1020202000202020222220202022222020200001"
            +  "1020202020202020002000202025202020222201"
            +  "1000200020000000200020002000000020000001"
            +  "1111111111111111111111111111111111111111";
    }
//        public String levelThree() {
//        return "11111111111111111111" 
//            +  "10000020002000000031" 
//            +  "10222020202022222221"
//            +  "10200020202020002401"                
//            +  "10202220200020202201"                
//            +  "10200000222220202001"                
//            +  "10222222200000200021"                
//            +  "10202000202222222221"                
//            +  "10202020200000000001"                
//            +  "10250020202222222201"
//            +  "10222220200002000201"
//            +  "10000000202202020201"
//            +  "10222222252200020201"                
//            +  "10000000222222220201"                
//            +  "12222220200022220201"                
//            +  "10002000202000000201"                
//            +  "10202020202022220201"                
//            +  "10202020202020220201"                
//            +  "10200020002020000201"                
//            +  "11111111111111111111";
//    }
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