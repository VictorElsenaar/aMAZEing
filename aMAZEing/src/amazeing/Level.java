/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            // OUDE VAK LEEG MAKEN
            Figuur empty = new Leeg();
            oudeVak.setFiguur(empty);
            doolhofMap.set(tempindex, oudeVak);
            
            // nieuwevak spelers object in plaatsen LET OP NOG NAKIJKEN OF ER NOG OPPAKBARE DINGEN LIGGEN
            Speler speler = new Speler();
            nieuweVak.setFiguur(speler);
            doolhofMap.set(tempindex+value,nieuweVak);
            revalidate();
            repaint();
        }
    }
    public boolean isMuur(Vak nieuwevak){
        if(nieuwevak.getFiguur().getNaam().equals("muur")) {
            return true;
        }
        return false;
    }
    public boolean isVriend(Vak nieuwevak) {
        if(nieuwevak.getFiguur().getNaam().equals("vriend")) {
            return true;
        }
        return false;
    }
    public void setLevel(String level) {
        System.out.println(level);

        Muur muur = new Muur();
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
                // Als het een 1 is, dan een muur plaatsen
                if(Integer.parseInt(typeOnPosition) == 1) {
                    vak = new Vak(x,y,muur);
                // Als het een 2 is dan een speler plaatsen
                } else if (Integer.parseInt(typeOnPosition) == 2) {
                    vak = new Vak(x,y,speler);
                } else if (Integer.parseInt(typeOnPosition) == 3) {
                    vak = new Vak(x,y,vriend);
                } else if (Integer.parseInt(typeOnPosition) == 4) {
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
        
    
    public String levelOne() {
      return  "1111111111"
            + "1210100001"
            + "1010101011"
            + "1010101001"
            + "1010041101"
            + "1010110001"
            + "1010100111"
            + "1010101131"
            + "1000100001"
            + "1111111111";
    }   
    public String levelTwo() {
      return  "1111111111"
            + "1120000001"
            + "1010101101"
            + "1010100001"
            + "1010111111"
            + "1000110001"
            + "1011000101"
            + "1010011101"
            + "1000113001"
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