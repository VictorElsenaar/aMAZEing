/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
//import static amazeing.AMAZEing.speler;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author vic
 */
public class Level extends JComponent{
    private final int LEVEL_FRAME_SIZE = 500;
    private final int VAKGROOTTE = 25;
    
    private final int MAZESIZE = 10; // Always square
    
    private LinkedList<Vak> doolhofMap;
    
    private Vak spelersVak;
    
    
    public Level() {
        setLevel(levelOne());
        if(debug){readLevel();} // controleer het level
           
    }
    
                           //public LinkedList<Vak> move(String richting, LinkedList<Vak> doolhofMap, int mazesize, Vak spelersVak) {
    public void move(String richting) {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();   
        doolhofMap = huidigeSpeler.move(richting, doolhofMap, MAZESIZE, spelersVak );
        revalidate();
        repaint();
    }
    
    /*
    public void move_left() {
        move(-1);
    } 
    public void move_right() {
        move(1);
    }    
    public void move_up() {
        move(-MAZESIZE);
    }
    public void move_down() {
        move(MAZESIZE);       
    }
    public void move(int value) {
        int tempindex=0;
        tempindex = doolhofMap.indexOf(spelersVak);
        Vak oudeVak = doolhofMap.get(tempindex);
        Vak nieuweVak = doolhofMap.get(tempindex+value);
        if (isVriend(nieuweVak)) {
             JOptionPane.showMessageDialog(null, "Vriend gevonden! gefeliciteerd!");
        }
        if(!isMuur(nieuweVak)) {
            // Oude vak speler ophalen
            Speler huidigeSpeler = (Speler) oudeVak.getFiguur();

            // OUDE VAK LEEG MAKEN
            Figuur empty = new Leeg();
            oudeVak.setFiguur(empty);
            doolhofMap.set(tempindex, oudeVak);
            
            // controleer of er op het vak iets anders staat, voor nu alleen bazooka
            if(isBazooka(nieuweVak)) {
                huidigeSpeler.addBazooka();
            }
            System.out.println("Aantal bazooka's = " + huidigeSpeler.getCount());
            
            // nieuwevak spelers object in plaatsen LET OP NOG NAKIJKEN OF ER NOG OPPAKBARE DINGEN LIGGEN
            nieuweVak.setFiguur(huidigeSpeler);
            doolhofMap.set(tempindex+value,nieuweVak);
            revalidate();
            repaint();
        }
    }*/
    
    /*
    public void fire_down() {
        fire(MAZESIZE);
    }
    public void fire_up() {
        fire(-MAZESIZE);
    }
    public void fire_right() {
        fire(1);
    }
    public void fire_left() {
        fire(-1);
    }    
    public void fire(int value) {
        int currentLocationIndex = doolhofMap.indexOf(spelersVak);
        Vak schietvak = doolhofMap.get(currentLocationIndex+value);
        int i = 1;
        while(!isMuur(schietvak)) {
            schietvak = doolhofMap.get(currentLocationIndex+(value*i));
            i++;
        }
        if(isMuur(schietvak)) { 
            // muur vak gevonden dus afhandelen.
            if(debug) {System.out.println("vakje is een muur, dus kogel afhandelen");}
            Muur muur = (Muur) schietvak.getFiguur();
            if(muur.getBorderMuur()){
                if(debug) {System.out.println("Bordermuur kan niet kapot");}
                // Bordermuur dus kogel is verloren, animatie moet het duidelijk maken
            } else {
                if(debug) {System.out.println("Normale muur is stuk!");}
                Figuur empty = new Leeg();
                schietvak.setFiguur(empty);
                repaint();
            }
        }
        
    }   
    */

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
            g.setColor(figuur.getkleur());
            // x en y as lijken omgedraaid te moeten...
            g.fillRect((vak.gety()*VAKGROOTTE), (vak.getx()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE);
            /*
            g.setColor(Color.GRAY);
            g.fillRect((vak.gety()*VAKGROOTTE)+2, (vak.getx()*VAKGROOTTE)+2, VAKGROOTTE-4, VAKGROOTTE-4);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect((vak.gety()*VAKGROOTTE)+4, (vak.getx()*VAKGROOTTE)+4, VAKGROOTTE-8, VAKGROOTTE-8);
            */
            if(figuur.getNaam().equals("bazooka")) {
                BufferedImage image; 
                try {
                    image = ImageIO.read(new File("..\\aMAZEing\\src\\amazeing\\bazooka.jpeg"));
                    g.drawImage(image, (vak.gety()*VAKGROOTTE), (vak.getx()*VAKGROOTTE), null);
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
        return MAZESIZE;
    }
    public Vak getSpelersVak() {
        return spelersVak;
    }
      
    public void setLevel(String level) {
        System.out.println(level);

        Muur muur = new Muur();
        Muur buitenmuur = new Muur(true);
        Figuur empty = new Leeg();
        Speler speler = new Speler();
        Vriend vriend = new Vriend();
        Bazooka bazooka = new Bazooka();
        
        doolhofMap = new LinkedList<>();
         
        int counter = 0;
        for (int x = 0; x < MAZESIZE ; x++) {
            if(debug){System.out.println("rows " + x);}
            for(int y = 0; y < MAZESIZE ; y++) {
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