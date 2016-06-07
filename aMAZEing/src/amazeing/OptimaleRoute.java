package amazeing;

import static amazeing.AMAZEing.THEME;
import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Helper classen voor het vinden van de snelste route
 * @author Victor Elsenaar en Kahoo Wu
 */
public class OptimaleRoute extends JComponent implements Runnable{
    
    private ArrayList<JPanel> kortste_route_panels;
    private LinkedList<Integer> k_r;
    private int vak_size_pixels;
    private ArrayList<Vak> doolhofMap;
    private int current_maze_size;
    private Vak startVak;
    private Vak eindVak;    
    
    
    
    
    private String theme;
    private BufferedImage optimalerouteImage;
    
    private static boolean optdebug = false;
    
    private static LinkedList<LinkedList> te_verwerken_routes = new LinkedList<LinkedList>();
    private static LinkedList<Integer> alternatieve_route = new LinkedList<Integer>();
    private static LinkedList<LinkedList> gevonden_routes = new LinkedList<LinkedList>();
    private static LinkedList<Integer> kortste_route = new LinkedList<Integer>();   
    private static ArrayList<Integer> richtingen = new ArrayList<Integer>();
    private static LinkedList<Integer> huidige_route = new LinkedList<Integer>();
    

    //Constructor
    public OptimaleRoute(int vak_size_pixels, String theme) {
        setLayout(null);
        setSize(Level.global_vak_size_pixels, Level.global_vak_size_pixels); // maakt gebruik van public static global_vak_size_pixels 
        this.theme = theme;        
        initialiseerImage();
    }    
    public OptimaleRoute(int vak_size_pixels, String theme,ArrayList<Vak> doolhofMap, int current_maze_size, Vak startVak, Vak eindVak) {
        setLayout(null);
        setSize(Level.global_vak_size_pixels, Level.global_vak_size_pixels); // maakt gebruik van public static global_vak_size_pixels 
        this.theme = theme;        
        initialiseerImage();
        
        this.vak_size_pixels = vak_size_pixels;
        this.doolhofMap = doolhofMap;
        this.current_maze_size = current_maze_size;
        this.startVak = startVak;
        this.eindVak = eindVak;
        
//        k_r = vindRoute(doolhofMap, current_maze_size, startVak, eindVak);
//        toonOptimaleRouteInner(k_r, doolhofMap,vak_size_pixels);
    }
    
    /**
     * Vraag de optimale route op van de doolhof op basis van een start en eind vak.
     * @param doolhofMap
     * @param current_maze_size
     * @param startVak
     * @param eindVak
     * @return 
     */
    public static LinkedList<Integer> vindRoute(ArrayList<Vak> doolhofMap, int current_maze_size, Vak startVak, Vak eindVak) {
        // Opschonen te gebruiken parameters.
        initialiseren();
        vul_richtingen(current_maze_size);

        int start_vak = doolhofMap.indexOf(startVak);
        huidige_route.add(start_vak);
        
        // Bij het nalopen van de richtingen wordt deze teller gebruikt indien er meer dan 1 extra stap te nemen is.
        int nog_opties_teller = 0;
        // Wanneer nog_opties_teller 2 of meer is dan zal deze boolean ervoor zorgen dat er alternatieve routes worden gemaakt.
        boolean ga_alternatieve = false;

        while(true){
            nog_opties_teller = 0;
            int nieuwe_stap = 0;
            ga_alternatieve = false;
            for(int richting : richtingen) {
                int huidige_stap_positie = start_vak+richting;
                Vak oude_start_vak = doolhofMap.get(start_vak);
                Vak tijdelijk_vak = doolhofMap.get(huidige_stap_positie);
                                                    if(optdebug){System.out.println("@@@@ VOLGEND VAKJE @@@@");}
                                                    if(optdebug){System.out.println("oude positie " + start_vak + " coords " + oude_start_vak.toString());}
                                                    if(optdebug){System.out.println("newe positie " + huidige_stap_positie + " coords " + tijdelijk_vak.toString());}
                                                    if(optdebug){System.out.println("@@@ END");}
                if(!tijdelijk_vak.isMuur(tijdelijk_vak)) {
                    // Als vak nog niet gebruikt is in huidige route, dan toevoegen en instellen als nieuw vertrekpunt
                    if(!is_gebruikt_vak(huidige_stap_positie)) {
                        nog_opties_teller++;

                        if(ga_alternatieve) {
                                                    if(optdebug){System.out.println("alternatieve route toegevoegd nog_opties_teller groter dan 1 = " + nog_opties_teller);}                            
                            LinkedList<Integer> alternatieve_route = (LinkedList<Integer>) huidige_route.clone();
                            alternatieve_route.add(huidige_stap_positie);
                            // Alternatieve route toevoegen aan een array met alternatieve routes.
                            te_verwerken_routes.add(alternatieve_route);
                                                    if(optdebug){System.out.println("Alternatieve_route is dus gevonden en deze is zo lang: " + alternatieve_route.size());}
                                                    if(optdebug){System.out.println("te verwerken routes 1 toegevoegd, stand is nu " + te_verwerken_routes.size());}                          
                        } else {
                            ga_alternatieve = true;
                            nieuwe_stap = huidige_stap_positie;
                            // Controleer of het vak waar je op uitkomt het doel vak is.
                            Vak eind_vak_huidige_route = doolhofMap.get(huidige_stap_positie);
                            if(eind_vak_huidige_route == eindVak){
                                huidige_route.add(huidige_stap_positie);
                                                    if(optdebug){System.out.println("route gevonden------------------------------------------------------");}
                                LinkedList<Integer> route_gevonden = (LinkedList<Integer>) huidige_route.clone();
                                // Sla gevonden route op in een array van gevonden_routes.
                                gevonden_routes.add(route_gevonden);
                                if(kortste_route.isEmpty()) {
                                    kortste_route = (LinkedList<Integer>) huidige_route.clone();
                                }
                                if(!kortste_route.isEmpty() && huidige_route.size() < kortste_route.size()) {
                                    kortste_route = (LinkedList<Integer>) huidige_route.clone();
                                }
                                // Eind vak is gevonden dus andere richtingen hebben geen nut. Stop de For richtingen loop.
                                nog_opties_teller = 0;
                                break;
                            }
                        }
                        // als huidige route al langer is dan huidig korste route, dan gelijk afbreken.
                        if(!kortste_route.isEmpty() && huidige_route.size() > kortste_route.size()) {
                            nog_opties_teller = 0;
                            break;
                        }
                    }
                }  
            } // Dit is einde For
            
            huidige_route.add(nieuwe_stap);
            
            // Alle richtingen zijn bepaald dus nu met huidige route verder.
            start_vak = huidige_route.getLast();
                                                    if(optdebug){System.out.println("uit de hele route bepaling afhankelijk van nog opties teller doorgaan, als 0 is dan stoppen : " + nog_opties_teller);}
                                                    if(optdebug){System.out.println("is te verwerkenroutes 0 dan afbreken " + te_verwerken_routes.size());}
            // Er zijn in de huidige route geen opties meer en de te verwerken routes zijn ook verwerkt, dus klaar met zoeken van routes.
            if(nog_opties_teller == 0 && te_verwerken_routes.size() == 0) {
                break;
            }
            // Als alleen op de huidige route geen opties meer zijn dan moet we een alternatieve route erbij pakken.
            if(nog_opties_teller == 0) {
                                                    if(optdebug){System.out.println("Er zijn geen opties meer in deze route");}
                                                    if(optdebug){System.out.println("te verwerken routes " + te_verwerken_routes.size());}
                huidige_route.clear();
                huidige_route.addAll(te_verwerken_routes.pop());
                                                    if(optdebug){System.out.println("huidige route formaat " + huidige_route.size());}
                start_vak = huidige_route.getLast();
            } 
        }// Dit is einde While
        
                                                    if(optdebug){System.out.println("te verwerken routes" + te_verwerken_routes.size());}
                                                    if(optdebug){System.out.println("gevonden_routes " + gevonden_routes.size());}  
        if(gevonden_routes.size() > 0) {
                                                    if(optdebug){System.out.println("Er zijn routes gevonden dus");}
            huidige_route.clear();
            huidige_route.addAll(gevonden_routes.pop());
                                                    if(optdebug){System.out.println(huidige_route.size());}
                                                    if(optdebug) {
                                                        for(int pad : huidige_route){
                                                            System.out.println("huidige_route" + pad);
                                                        }
                                                    }
        } else {
            // Dit zou betekenen dat er een doolhof is zonder uitkomst.
            if(optdebug){System.out.println("Er zijn geen routes gevonden!");}
        }
                                                    if(optdebug){System.out.println("Toon eind lijst");}
                                                    if(optdebug){System.out.println(huidige_route.size());}
                                                    if(optdebug){System.out.println("de kortste " + kortste_route.size());}
        return kortste_route;
    }
    /**
     * Opschonen van de parameters gebruikt voor het genereren van de optimale route.
     */
    private static void initialiseren() {
        te_verwerken_routes.clear();
        alternatieve_route.clear();
        gevonden_routes.clear();
        kortste_route.clear();
        huidige_route.clear();
        richtingen.clear();        
    }
    private static boolean is_gebruikt_vak (int indexnummer) {
        for (int vak : huidige_route) {
            if(indexnummer == vak) {
                return true;
            }
        }
        return false;
    }
    /**
     * Op basis van de maat van de map worden de richtingen (aantal) bepaald.
     * @param current_maze_size 
     */
    private static void vul_richtingen(int current_maze_size) {
        richtingen.add(1);
        richtingen.add(-1);
        richtingen.add(-current_maze_size);
        richtingen.add(current_maze_size);
    }
    public void paint(Graphics g) {
        if (optimalerouteImage == null) {
            g.setColor(new Color(20,220,255,124));
            g.fillRect(0, 0, Level.global_vak_size_pixels, Level.global_vak_size_pixels);
        } else {
            g.drawImage(optimalerouteImage.getScaledInstance(Level.global_vak_size_pixels,Level.global_vak_size_pixels,0), 0, 0, null);
        }
        //g.fillRect((vak.gety()*vak_size_pixels)+vak_size_pixels/4, (vak.getx()*vak_size_pixels)+vak_size_pixels/4, vak_size_pixels-(vak_size_pixels/2), vak_size_pixels-(vak_size_pixels/2));
    }
    public void initialiseerImage() {
        try {
            optimalerouteImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + theme + "\\\\optimaleroute.jpg")); 
        }
        catch (Exception e) {
            optimalerouteImage = null;
        }
    }    

   // @Override
    public void run() {
        System.out.println("hello");
        try {
            //bouwroute;
            k_r = vindRoute(doolhofMap, current_maze_size, startVak, eindVak);
            toonOptimaleRouteInner(k_r, doolhofMap,vak_size_pixels);
            Thread.sleep(2000);
            verdwijderOptimaleRouteInner(kortste_route_panels);
            if(debug){System.out.println("Optimale route uitgezet");}
        } catch (Exception e) {}
       
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void verdwijderOptimaleRouteInner(ArrayList<JPanel> kortste_route_panels){
        for (JPanel panel : kortste_route_panels) {         
            if (panel.getComponent(0).getName()!= null && panel.getComponent(0).getName().equals("OptimaleRoute")) {
                panel.remove(panel.getComponent(0));
                panel.repaint();
            }
        }       
    }
    private void toonOptimaleRouteInner(LinkedList<Integer> kortste_route,ArrayList<Vak> doolhofMap, int vak_size_pixels){
        ArrayList<JPanel> kortste_route_panels2 = new ArrayList<JPanel>();
        for (int i = 1; i < kortste_route.size()-1; i++) {
            
            Vak vak = doolhofMap.get(kortste_route.get(i));
            
            OptimaleRoute route = new OptimaleRoute(vak_size_pixels, THEME);
            route.setName("OptimaleRoute");
            JPanel panel = vak.getPanel();
            panel.add(route);
            panel.setComponentZOrder(route, 0);
            panel.repaint();
            
            kortste_route_panels2.add(panel);
            
            
        }        
        this.kortste_route_panels = kortste_route_panels2;
    }
}


/*
Pseudo code vinden van de snelste route.

Bepaal beginvak
Bepaal eindvak

Begin van beginvak
Bepaal welke kant men op kan gaan, controlleer hier dat deze niet reeds gebruikt is.
Creeer route voor elke richting. Sla op in een queue (vertrek plek + historie)
Ga met Last in First out het vervolg stap.
Indien men 1 kant op kan gaan, dan die toevoegen aan huidige test route + historie gekoppeld aan deze route dat dat vakje al geweest is.
Indien er geen leeg vlak meer dan verwijder huidige route en historie en pak volgende route optie uit de lijst OF als vriend gevonden dan opslaan in potientele routes OF het vakje is al gebruikt in de huidige route!
Indien alle routes geweest zijn en er zijn geen afslagen meer dan bepalen welke korste route had.
*/