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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
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
    
    private LinkedList<Vak> doolhofMap;
    
    private Vak spelersVak;
    
    

    
    public Level() {
        setLevel(levelOne());
        if(debug){readLevel();} // controleer het level
           
    }
    
    public void move_down() {
        System.out.println("blaaaaaaaaaaaaaaaaaaaaa");
        int tempindex=0;
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            if(vak.equals(spelersVak)) {
                Figuur figuur = vak.getFiguur();
                System.out.println(figuur.getNaam());
                
                // nu zijn we in de list beland op de spelerspositie.
                // maak het vak leeg want hij loopt naar beneden @@@@ CONTROLE NOG INBOUWEN OF HET UBERHAUPT WEL KAN :-)
                tempindex = doolhofMap.indexOf(spelersVak);
                Figuur empty = new Leeg();
                vak.setFiguur(empty);
                iterator.set(vak);
                
                Vak vak2 = doolhofMap.get(tempindex+10);
                Speler speler = new Speler();
                vak2.setFiguur(speler);

                doolhofMap.set(tempindex+10, vak2);
               
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

        
        //System.out.println("test");
        //System.out.println(doolhofMap.indexOf(spelersVak));
        //Vak vak = doolhofMap.set(tempindex, spelersVak)
        // in plaatsen van speler op map, bewaar key naar value in speler (hashcode key)
        
        
        
        // Haal op basis van key de values op
        // verwijder
        // maak nieuwe aan van nieuwe positie
//        System.out.println("voor vak");
//        System.out.println(speler.getx());
//        Vak vak = new Vak(speler.getVak().getXAs(),speler.getVak().getYAs());
//        System.out.println("na new vak");
//        System.out.println("voor remove");
//        map.remove(vak);
//        System.out.println("na remove");
//        map.put(new Vak(speler.getx()+1,speler.gety()),speler);

        
//        Set<Vak> keySet = map.keySet();
//        for (Vak key : keySet)
//        {
//            Figuur value = map.get(key);
//            if (value.getNaam().equals("speler")) { // als object een muur is
//                map.remove(value);
//                
//            } 
//        }         
        
//        Iterator<Map.Entry<Vak,Figuur>> iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry<Vak,Figuur> entry = iter.next();
//            if("speler".equalsIgnoreCase(entry.getValue().getNaam())){
//                int x = entry.getValue().getVak().getXAs();
//                int y = entry.getValue().getVak().getYAs();
//                iter.remove();
//                map.put(new Vak(y,x),speler);
//            }
//        }
        
//        for (Map.Entry<Vak,Figuur> entry : map.entrySet()) {
//            if(entry.getValue().getNaam().equals("speler")){
//                System.out.println("test");
////            System.out.println(entry.getKey().getCord() + "/" + entry.getValue().getNaam());
//               map.put(new Vak(entry.getKey().getYAs(),entry.getKey().getXAs()),speler);        
//               entry.
//            }
//
//        }
        
        
        //readLevel();
    }
    
    public void setLevel(String level) {
        System.out.println(level);

        Muur muur = new Muur();
        Figuur empty = new Leeg();
        Speler speler = new Speler();
        
        
        
        map = new HashMap<>();
        
        doolhofMap = new LinkedList<>();
        
        
        
        int counter = 0;
        for (int x = 0; x < ROWS ; x++) {
            if(debug){System.out.println("rows " + x);}
            for(int y = 0; y < COLUMNS ; y++) {
                Vak vak;
                if(debug){System.out.println("columns " + y);}
                String typeOnPosition = level.substring(counter, counter+1);
                if(debug){System.out.println(typeOnPosition);}
                if(Integer.parseInt(typeOnPosition) == 1) {
                     
                    //map.put(new Vak(y,x),muur);
                    vak = new Vak(x,y,muur);
                    
                    
                    
                   // map[x][y] = new Tile(new Figure("Muur"));
                } else if (Integer.parseInt(typeOnPosition) == 2) {
                    vak = new Vak(x,y,speler);                    
                    
//                    map.put(new Vak(y,x),speler);
//                    speler.setx(y);
//                    speler.sety(x);
//                    speler.setVak(new Vak(y,x));
//                    System.out.println("Victor: " + speler.getx());
                   // map[x][y] = new Tile(new Figure("Speler"));
                } else
                {
                    //map.put(new Vak(y,x),empty);
                   // map[x][y] = new Tile(new Figure("Leeg"));
                    vak = new Vak(x,y,empty);
                }
                doolhofMap.add(vak); 
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
//        for (Map.Entry<Vak,Figuur> entry : map.entrySet()) {
//            System.out.println(entry.getKey().getCord() + "/" + entry.getValue().getNaam());      
//        }
        
        
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            System.out.println(vak.toString());
        }
        
    }
    
    public void paint(Graphics g) {
//        for (int i = 0; i < LEVEL_FRAME_SIZE; i+=VAKGROOTTE) {
//            for (int j = 0; j < LEVEL_FRAME_SIZE; j+=VAKGROOTTE) {
//              g.drawLine(i, j, LEVEL_FRAME_SIZE, j); // de grid tekenen
//              g.drawLine(i, j, i, LEVEL_FRAME_SIZE); // de grid tekenen
//            }
//        }
        
//////        Set<Vak> keySet = map.keySet();
//////        for (Vak key : keySet)
//////        {
//////            Figuur value = map.get(key);
//////            if (value.getNaam().equals("muur")) { // als object een muur is
//////                g.setColor(java.awt.Color.black);
//////                g.fillRect((key.getXAs()*VAKGROOTTE), (key.getYAs()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE); // vul de vakken zwart
//////            } else if (value.getNaam().equals("speler")) {
//////                g.setColor(java.awt.Color.red);
//////                g.fillRect((key.getXAs()*VAKGROOTTE), (key.getYAs()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE);
//////            }
//////        }
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            Figuur figuur = vak.getFiguur();
            g.setColor(figuur.getkleur());
            // x en y as lijken omgedraaid te moeten...
            g.fillRect((vak.gety()*VAKGROOTTE), (vak.getx()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE);
            
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
            + "1010001101"
            + "1010110001"
            + "1010100111"
            + "1010101101"
            + "1000100001"
            + "1111111111";
    }   
}
