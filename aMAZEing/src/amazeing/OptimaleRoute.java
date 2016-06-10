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
 * Klasse voor het vinden en tonen van de snelste route
 * @author Victor Elsenaar en Kahoo Wu
 */
public class OptimaleRoute extends JComponent implements Runnable{
    
    // Alle doorgegeven parameters in het object opslaan, aangezien dit object in een eigen thread alles gaat afhandelen afzonderlijk van het lopende spel.
    private ArrayList<JPanel> kortste_route_panels;
    private LinkedList<Integer> k_r;
    private int vak_size_pixels;
    private ArrayList<Vak> doolhofMap;
    private int current_maze_size;
    private Vak startVak;
    private Vak eindVak;    
    
    private BufferedImage optimalerouteImage;
    
    private LinkedList<LinkedList> te_verwerken_routes = new LinkedList<LinkedList>();
    private LinkedList<LinkedList> gevonden_routes = new LinkedList<LinkedList>();
    private LinkedList<Integer> kortste_route = new LinkedList<Integer>();   
    private ArrayList<Integer> richtingen = new ArrayList<Integer>();
    private LinkedList<Integer> huidige_route = new LinkedList<Integer>();
    

    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     */
    public OptimaleRoute(int vak_size_pixels) {
        initGraphical(vak_size_pixels);
        initialiseerImage();
    }    
    
    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     * @param doolhofMap = de map
     * @param current_maze_size = maat van de map
     * @param startVak = het vak waar de speler op dit moment staat
     * @param eindVak = het vak waar de vriend staat
     */
    public OptimaleRoute(int vak_size_pixels, String theme,ArrayList<Vak> doolhofMap, int current_maze_size, Vak startVak, Vak eindVak) {
        initGraphical(vak_size_pixels);
        initialiseerImage();
        
        this.vak_size_pixels = vak_size_pixels;
        this.doolhofMap = doolhofMap;
        this.current_maze_size = current_maze_size;
        this.startVak = startVak;
        this.eindVak = eindVak;
    }
    
    /**
     * Vraag de optimale route op van de doolhof op basis van een start en eind vak.
     * @return de kortste route
     */
    public LinkedList<Integer> vindRoute(){
        initialiseren(); // Opschonen te gebruiken parameters.
        vul_richtingen(current_maze_size); // richtingen bepalen, deze zijn afhankelijk van de maat van het huidige level
        int start_vak = doolhofMap.indexOf(startVak);
        huidige_route.add(start_vak);
        int nog_opties_teller = 0; // Bij het nalopen van de richtingen wordt deze teller gebruikt indien er meer dan 1 extra stap te nemen is.
        boolean ga_alternatieve = false; // Wanneer nog_opties_teller 2 of meer is dan zal deze boolean ervoor zorgen dat er alternatieve routes worden gemaakt.

        while(true){
            // Reset de gebruikte parameters
            nog_opties_teller = 0;
            int nieuwe_stap = 0;
            ga_alternatieve = false;
            
            for(int richting : richtingen) {
                int huidige_stap_positie = start_vak+richting;
                Vak tijdelijk_vak = doolhofMap.get(huidige_stap_positie);                
                if(!tijdelijk_vak.isMuur(tijdelijk_vak)) {
                    // Als vak nog niet gebruikt is in huidige route, dan toevoegen en instellen als nieuw vertrekpunt
                    if(!is_gebruikt_vak(huidige_stap_positie)) {
                        nog_opties_teller++;
                        if(ga_alternatieve) {                        
                            LinkedList<Integer> alternatieve_route = (LinkedList<Integer>) huidige_route.clone();
                            alternatieve_route.add(huidige_stap_positie);
                            // Alternatieve route toevoegen aan een array met alternatieve routes.
                            te_verwerken_routes.add(alternatieve_route);                
                        } else {
                            ga_alternatieve = true; // de volgende keer vanaf hetzelfde startvak is het een alternatieve route
                            nieuwe_stap = huidige_stap_positie;
                            // Controleer of het vak waar je op uitkomt het doel vak is.
                            Vak eind_vak_huidige_route = doolhofMap.get(huidige_stap_positie);
                            if(eind_vak_huidige_route == eindVak){
                                afhandelenGevondenRoute(huidige_stap_positie);
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
            } // Einde FOR
            huidige_route.add(nieuwe_stap);
            start_vak = huidige_route.getLast(); // Alle richtingen zijn bepaald dus nu met huidige route verder.

            // Er zijn in de huidige route geen opties meer en de te verwerken routes zijn ook verwerkt, dus klaar met zoeken van routes.
            if(nog_opties_teller == 0 && te_verwerken_routes.size() == 0) {
                break; // break de while
            }
            // Als alleen op de huidige route geen opties meer zijn dan moet we een alternatieve route erbij pakken.
            if(nog_opties_teller == 0) {
                huidige_route.clear();
                huidige_route.addAll(te_verwerken_routes.pop());
                start_vak = huidige_route.getLast();
            } 
        }// Einde WHILE
        if(gevonden_routes.size() > 0) { // Er zijn nog routes die niet verder uitgezocht zijn, dus gaan we deze instellen als huidige route.
            huidige_route.clear();
            huidige_route.addAll(gevonden_routes.pop());
        } else {
            // Dit zou betekenen dat er een doolhof is zonder uitkomst.
        }
        return kortste_route;
    }
    /**
     * Methode slaat de gevonden route op en bepaald gelijk of dit de kortste route is.
     * @param huidige_stap_positie = de index positie waarin de nieuwe stap op de doolhofmap zich bevindt
     */
    private void afhandelenGevondenRoute(int huidige_stap_positie) {
        huidige_route.add(huidige_stap_positie);
        LinkedList<Integer> route_gevonden = (LinkedList<Integer>) huidige_route.clone();
        // Sla gevonden route op in een array van gevonden_routes.
        gevonden_routes.add(route_gevonden);
        if(kortste_route.isEmpty()) {
            kortste_route = (LinkedList<Integer>) huidige_route.clone();
        }
        if(!kortste_route.isEmpty() && huidige_route.size() < kortste_route.size()) {
            kortste_route = (LinkedList<Integer>) huidige_route.clone();
        }
    }
    /**
     * Opschonen van de parameters gebruikt voor het genereren van de optimale route.
     */
    private void initialiseren() {
        te_verwerken_routes.clear();
        gevonden_routes.clear();
        kortste_route.clear();
        huidige_route.clear();
        richtingen.clear();        
    }
    /**
     * Methode om na te kijken of het vak reeds gebruikt is in de huidige route.
     * @param indexnummer = indexnummer van de positie waar de optimale route berekening reeds geweest kan zijn
     * @return 
     */
    private boolean is_gebruikt_vak (int indexnummer) {
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
    private void vul_richtingen(int current_maze_size) {
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
    
    /**
     * Plaatje wordt in de buffer gezet
     */
    public void initialiseerImage() {
        try {
            optimalerouteImage = ImageIO.read(new File("..\\\\aMAZEing\\\\src\\\\amazeing\\\\theme\\\\" + THEME + "\\\\optimaleroute.jpg")); 
        }
        catch (Exception e) {
            optimalerouteImage = null;
        }
    }    
    // Thread zorgt ervoor dat asynchoon het pad gevonden, getoond en verwijderd wordt.
    public void run() {
        try {
            k_r = vindRoute();
            toonOptimaleRouteInner(k_r, doolhofMap,vak_size_pixels);
            Thread.sleep(2000);
            verdwijderOptimaleRouteInner(kortste_route_panels);
            if(debug){System.out.println("Optimale route uitgezet");}
        } catch (Exception e) {}
    }
    /**
     * Deze methode zorgt ervoor dat de panelen weer verwijderd worden, zodat de optimale route tijdelijk getoond kan worden.
     * @param kortste_route_panels = de opgebouwde panelen van de korte route.
     */
    private void verdwijderOptimaleRouteInner(ArrayList<JPanel> kortste_route_panels){
        for (JPanel panel : kortste_route_panels) {         
            if (panel.getComponent(0).getName()!= null && panel.getComponent(0).getName().equals("OptimaleRoute")) {
                panel.remove(panel.getComponent(0));
                panel.repaint();
            }
        }       
    }
    /**
     * Methode zorgt ervoor dat de panelen gemaakt worden die de optimale route omvatten en deze intekent met het geconfigureerde plaatje.
     * @param kortste_route = reeds bepaalde kortste route
     * @param doolhofMap = de map
     * @param vak_size_pixels = de maat van een vak
     */
    private void toonOptimaleRouteInner(LinkedList<Integer> kortste_route,ArrayList<Vak> doolhofMap, int vak_size_pixels){
        ArrayList<JPanel> kortste_route_panels2 = new ArrayList<JPanel>();
        for (int i = 1; i < kortste_route.size()-1; i++) {
            Vak vak = doolhofMap.get(kortste_route.get(i));
            OptimaleRoute route = new OptimaleRoute(vak_size_pixels);
            route.setName("OptimaleRoute");
            JPanel panel = vak.getPanel();
            panel.add(route);
            panel.setComponentZOrder(route, 0);
            panel.repaint();
            kortste_route_panels2.add(panel);
        }        
        this.kortste_route_panels = kortste_route_panels2;
    }
    private void initGraphical(int vak_size_pixels) {
        setLayout(null);
        setSize(vak_size_pixels, vak_size_pixels);
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