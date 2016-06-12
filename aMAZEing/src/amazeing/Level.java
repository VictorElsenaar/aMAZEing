package amazeing;

import static amazeing.AMAZEing.THEME;
import static amazeing.AMAZEing.debug;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Class level handelt alle interacties tussen de input van game (dus uiteindelijk van de speler) en de overige componenten af.
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Level extends JComponent{
    /**
     * Maat van het speelveld.
     */
    private final int LEVEL_FRAME_SIZE = 500;
    
    /**
     * globale variabele voor het definieren van de maat van een vak m.b.t. het tekenen. Wordt gebruikt in extends figuur objecten.
     */
    public static int global_vak_size_pixels;
    
    /**
     * maximaal aantal vakken op 1 lijn. (altijd vierkant) Max aantal blokken totaal zou zijn MAX_MAZE_SIZE * MAX_MAZE_SIZE
     */
    private final int MAX_MAZE_SIZE = 50; 
    // Bij het inladen van het level wordt deze maat bewaard van de huidige maze.
    private int current_maze_size;
    // en de maat in pixels van een vak
    private int vak_size_pixels;

    private ArrayList<String> levels = new ArrayList<String>();  
    private ArrayList<Vak> doolhofMap;    
    private Vak spelersVak;
    private Vak vriendVak;
    private Vak vijandsVak;
    
    private int currentLevel;
    
    // Alleen voor de speler
    private boolean toonOptimaleRoute = false;

    // Alleen voor de vijand
    private Runnable r3 = new MyThread();
    private boolean shutdown;
    private LinkedList<Integer> kortste_route;
    
    /**
     * Nieuw instantie van dit object
     * @param level = het level dat ingeladen wordt
     */
    public Level(int level) {
        setLayout(null);
        addLevels();
        setLevel(level);
        if(debug){readLevel();} // controleer het level    
    }
    public int getStappen() {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();
        return huidigeSpeler.getaantalStappen();
    }
    public int getKogels() {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();
        return huidigeSpeler.getKogels();
    }
    public int getHelper() {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();
        return huidigeSpeler.getHelper();
    }
    /**
     * Methode dat de actie van de speler uitvoert. Indien er een richting is wordt deze gebruikt en anders genegeerd.
     * @param direction = richting waarin de actie moet plaatsvinden
     * @param type = bewegen, schieten of optimale route tonen is ook een actie type
     */
    public void action(String direction, String type) {
        Speler huidigeSpeler = (Speler) spelersVak.getFiguur();
        switch(type) {
            case "move":
                doolhofMap = huidigeSpeler.move(direction, doolhofMap, current_maze_size, spelersVak );
                spelersVak = huidigeSpeler.getVak();
                break;
            case "fire":
                doolhofMap = huidigeSpeler.fire(direction, doolhofMap, current_maze_size, spelersVak );
                break;
            case "optimal_route":
                if (huidigeSpeler.activeerOptimaleRoute()) {
                    toonOptimaleRoute();
                }
                break;
            default:
                break;
        }
        if(debug){System.out.println(spelersVak.toString());}
        if(debug){System.out.println(vriendVak.toString());}       
    }
    /**
    * Optimale route laten tonen. Methode start eigen thread waardoor overige activiteiten door blijven gaan.
    */
    public void toonOptimaleRoute() {
        Runnable r2 = new OptimaleRoute(vak_size_pixels, THEME, doolhofMap, current_maze_size, spelersVak, vriendVak);
        new Thread(r2).start();   
    }
    /**
     * Laat de vijand bewegen in de richting van de speler.
     */
    public void vijandBeweeg() {
        try {
            OptimaleRoute route = new OptimaleRoute(vak_size_pixels, THEME, doolhofMap, current_maze_size, vijandsVak, spelersVak);
            kortste_route = route.vindRoute(); // haal de snelste route op om naar de speler toe te gaan
            Vijand vijand = (Vijand) vijandsVak.getFiguur();
            Figuur tempFiguur = null;

            for (int i = 1; i < 3; i++) { // om de 2 stappen moeten we deze methode opnieuw uitvoeren           
                if(shutdown) {break;}
                Vak nieuweVak = doolhofMap.get(kortste_route.get(i));
                Vak oudeVak = vijandsVak;

                JPanel oudePanel = oudeVak.getPanel();
                if (tempFiguur == null) {
                    Figuur empty = new Leeg(vak_size_pixels, THEME); 
                    oudeVak.setFiguur(empty);
                    oudePanel.removeAll();
                    oudePanel.add(empty);
                } else {
                    oudeVak.setFiguur(tempFiguur);
                    oudePanel.removeAll();
                    oudePanel.add(tempFiguur);
                }
                oudePanel.repaint();
                tempFiguur = nieuweVak.getFiguur(); // sla tijdelijk de figuur van het nieuwevak op

                nieuweVak.setFiguur(vijand);
                JPanel panel = nieuweVak.getPanel();
                panel.removeAll();
                panel.add(vijand);
                panel.repaint();
                vijandsVak = nieuweVak; // update vijandsVak positie
                if(tempFiguur instanceof Speler) {
                    stopVijand();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }   
        } catch (Exception e) {}
    }
    
    class MyThread implements Runnable {
        @Override
        public void run() {
            while (!shutdown) {
                vijandBeweeg();
            }
        }
    }
    /**
     * Start de vijand met lopen. Clear eerst oude gegevens indien nodig.
     */
    public void startVijand() {
        kortste_route = null;
        shutdown = false;
        new Thread(r3).start();
    }
    /**
     * Stop de vijand met lopen door in de while van de runnable de boolean op true te zetten.
     */
    public void stopVijand() {
        shutdown = true;
    }    
    /**
     * Geeft gehele map in een ArrayList van vakken terug.
     * @return ArrayList<Vak>
     */
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
    public Vak getVijandsVak() {
        return vijandsVak;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }  
    public int getLevelsSize(){
        return levels.size();
    }
    /**
     * Method geeft een String terug van het gevraagde level.
     * @param nr = het index nummer van het level
     * @return 
     */
    public String getLevel(int nr) {
        if(debug){System.out.println("getLevel " + nr);}
        String level = levels.get(nr);
        if(debug){System.out.println(level);}
        return level;
    }    
    /**
     * Verhoogt het level met 1.
     */
    public void setNextLevel() {
        this.currentLevel++;
        if(currentLevel <= levels.size()-1) {
            this.setLevel(currentLevel);
        } else {
            this.currentLevel--;
        }
    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    /**
     * Methode bouwt de doolhofMap op op basis van het gekozen level.
     * @param nr = level index nummer
     */
    public void setLevel(int nr) {
        String level = getLevel(nr);
        if(debug){System.out.println("setcurrentlevel " + nr);}
        setCurrentLevel(nr);
        // Bepaal de breedte en hoogte
        for (int i = 1; i < MAX_MAZE_SIZE; i++) {
            if((level.length() / i) == i){
                current_maze_size = i;
            }
        }
        // Bepaal aantal pixels voor de map, zodat hij netjes het frame vult.
        vak_size_pixels = LEVEL_FRAME_SIZE / current_maze_size;
        global_vak_size_pixels = vak_size_pixels;  // voorlopig nodig voor Bom en Explosie
        
        if(debug){System.out.println(level);}        
        
        doolhofMap = new ArrayList<Vak>();
        
        int teleport = 0;
        Teleport teleport1 = null;
        Teleport teleport2 = null;
        int counter = 0;
        for (int x = 0; x < current_maze_size ; x++) {
            if(debug){System.out.println("rows " + x);}
            for(int y = 0; y < current_maze_size ; y++) {
                Vak vak;
                if(debug){System.out.println("columns " + y);}
                String typeOnPosition = level.substring(counter, counter+1);
                if(debug){System.out.println(typeOnPosition);}
                
                Figuur figuur = null;
                switch(Integer.parseInt(typeOnPosition)) {
                    case 1: // Als het een 1 is, dan een buitenmuur plaatsen
                        figuur = new Muur(true, vak_size_pixels, THEME);
                        break;
                    case 2: //Als het een 2 is dan een binnenmuur plaatsen
                        figuur = new Muur(vak_size_pixels, THEME);
                        break;
                    case 3: // Als het een 3 is dan een speler plaatsen
                        figuur = new Speler(vak_size_pixels, THEME);
                        break;
                    case 4: // Als het een 4 is dan een vriend plaatsen
                        figuur = new Vriend(vak_size_pixels, THEME);
                        break; 
                    case 5: // Als het een 5 is dan een bazooka plaatsen
                        figuur = new Bazooka(vak_size_pixels, THEME);
                        break;
                    case 6: // Als het een 6 is dan een helper plaatsen
                        figuur = new Helper(vak_size_pixels, THEME);
                        break;
                    case 7: // Als het een 7 is dan een cheater plaatsen
                        figuur = new Cheater(vak_size_pixels, THEME);
                        break;
                    case 8: // Als het een 8 is dan een teleport plaatsen                        
                        figuur = new Teleport(vak_size_pixels, THEME);
                        if (teleport == 0) {
                            teleport++;
                            teleport1 = (Teleport) figuur;
                            teleport1.setLocationIndex(doolhofMap.size());
                        } else {
                            teleport2 = (Teleport) figuur;
                            teleport2.setLocationIndex(doolhofMap.size());
                        }
                        break;
                    case 9: // Als het een 9 is dan een vijand plaatsen
                        figuur = new Vijand(vak_size_pixels, THEME);
                        break;
                    case 0: // Als het een 0 is dan een empty plaatsen
                        figuur = new Leeg(vak_size_pixels, THEME);
                        break;
                }
                vak = new Vak(x,y,figuur);
                if (Integer.parseInt(typeOnPosition) == 3) {
                    // Plaats de referentie van het vak ook in de speler, zodat de speler weet waar hij is. (niet gebruiker!)
                    spelersVak = vak;
                    Speler speler = (Speler) figuur;
                    speler.setVak(vak);
                }
                if (Integer.parseInt(typeOnPosition) == 4) {
                    vriendVak = vak;
                }
                if (Integer.parseInt(typeOnPosition) == 9) {
                    vijandsVak = vak;
                }
                JPanel panel = vak.getPanel();
                panel.setBounds((vak.gety()*vak_size_pixels), (vak.getx()*vak_size_pixels), vak_size_pixels, vak_size_pixels);
                panel.add(figuur);
                add(panel);
                doolhofMap.add(vak); 
                counter++;
            }
        }
        if (teleport1 != null && teleport2 != null) {
            teleport1.setOther(teleport2);
            teleport2.setOther(teleport1);
        }
    }    
    /**
     * Opbouwen van de Array levels met alle geconfigureerde levels.
     */
    public void addLevels() {
        String level_one ="1111111111"
                        + "1327200071"
                        + "1026202021"
                        + "1020202001"
                        + "1020052201"
                        + "1020220001"
                        + "1020200221"
                        + "1020202241"
                        + "1090200001"
                        + "1111111111";
        
        String level_two ="1111111111"
                        + "1230000001"
                        + "1020202201"
                        + "1020260071"
                        + "1020222221"
                        + "1007220001"
                        + "1022000201"
                        + "1020022201"
                        + "1000224001"
                        + "1111111111";
        
        String level_three =   "11111111111111111111" 
                            +  "10000028702000000031" 
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
                            +  "10002000202009000201"                
                            +  "10202020202022220201"                
                            +  "10202020202000820201"                
                            +  "16200720002020000001"                
                            +  "11111111111111111111";
                                
        String level_four =    "1111111111111111111111111111111111111111"
                            +  "1000002000200002000000000224222222000001"
                            +  "1020202020202222022222020020000000022201"
                            +  "1023202020000000026000020222222222020201"
                            +  "1022202022222222222222020020000000020201" 
                            +  "1020202000000000000000022220222202220201"
                            +  "1020202022222222002220002020007202000201"
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
                            +  "1020272222202020200022222222222020002201"
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
                            +  "1000200020000000200020002000000020000091"
                            +  "1111111111111111111111111111111111111111";
             
        levels.add(level_one);
        levels.add(level_two);
        levels.add(level_three);
        levels.add(level_four);
    }
    /**
     * Methode om het level uit te lezen, heeft geen doelen in het spel verder.
     */
    public void readLevel() {
        ListIterator<Vak> iterator = doolhofMap.listIterator();
        while(iterator.hasNext()) {
            Vak vak = iterator.next();
            if(debug){System.out.println(vak.toString());}
        }
    }    
}
