/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Helper classen voor het vinden van de snelste route
 * @author vic
 */
public class OptimaleRoute {
    
    // TODO
    // wanneer een nieuwe route al langer is dan een reeds gevonden route, dan gelijk afkappen.
    
    
    private static LinkedList<LinkedList> te_verwerken_routes = new LinkedList<LinkedList>();
    private static LinkedList<Integer> alternatieve_route = new LinkedList<Integer>();
    
    private static LinkedList<LinkedList> gevonden_routes = new LinkedList<LinkedList>();
    
    private static LinkedList<Integer> kortste_route = new LinkedList<Integer>();
    
    private static ArrayList<Integer> richtingen = new ArrayList<Integer>();
    // Opslaan van indexnummers van de route. (indexnummer op volgorde van invoer, 
    //FIFO is de te volgen route indien volgen nodig is, anders is het tonen en dat mag volledig random
    private static LinkedList<Integer> huidige_route = new LinkedList<Integer>();
    
    public static void vindRoute(LinkedList<Vak> doolhofMap, int current_maze_size, Vak startVak, Vak eindVak) {
        // current_maze_size * current_maze_size = dus max indexnummer;
        
        vul_richtingen(current_maze_size);
//        Begin van beginvak
        int start_vak = doolhofMap.indexOf(startVak);
        huidige_route.add(start_vak);
        System.out.println("STARTPLEK " + start_vak);
        int nog_opties_teller = 0;
        boolean ga_alternatieve = false;
//        Bepaal welke kant men op kan gaan, controlleer hier dat deze niet reeds gebruikt is.

        while(true){
            nog_opties_teller = 0;
            ga_alternatieve = false;
            System.out.println(richtingen);
            for(int richting : richtingen) {
                int huidige_stap_positie = start_vak+richting;
                // Is deze stap een muur, en zijn er meer stappen? (De eerste is de huidige route, nieuwe worden een nieuwe route.
                Vak oude_start_vak = doolhofMap.get(start_vak);
                Vak tijdelijk_vak = doolhofMap.get(huidige_stap_positie);
                System.out.println("@@@@ VOLGEND VAKJE @@@@");
                System.out.println("oude positie " + start_vak + " coords " + oude_start_vak.toString());
                System.out.println("newe positie " + huidige_stap_positie + " coords " + tijdelijk_vak.toString());
                System.out.println("@@@ END");
                if(!tijdelijk_vak.isMuur(tijdelijk_vak)) {
                    // Als vak nog niet gebruikt is in huidige route, dan toevoegen en instellen als nieuw vertrekpunt
                    if(!is_gebruikt_vak(huidige_stap_positie)) {
                        nog_opties_teller++;
                        // Als we meer dan richtingen vinden in onze huidige route
                        // dan vinden we een splitsing, dus dan moeten we de huidige volledige route 
                        // KOPIEREN !!! en de nieuwe stap daar in opslaan ipv in de huidige_route!
                       // if(nog_opties_teller > 1) {
                        if(ga_alternatieve) {
                            LinkedList<Integer> alternatieve_route = (LinkedList<Integer>) huidige_route.clone();
                            System.out.println("alternatieve route toegevoegd nog_opties_teller groter dan 1 = " + nog_opties_teller);
                            //alternatieve_route.clear();
                            //alternatieve_route.addAll(huidige_route);
                            
//                            LinkedList<Integer> alternatieve_route = new LinkedList<Integer>();
//                            for(int pad : huidige_route) {
//                                int copy = pad;
//                                alternatieve_route.add(copy);
//                            }
                            alternatieve_route.add(huidige_stap_positie);
                            te_verwerken_routes.add(alternatieve_route);
                            System.out.println("Alternatieve_route is dus gevonden en deze is zo lang: " + alternatieve_route.size());
                            System.out.println("te verwerken routes 1 toegevoegd, stand is nu " + te_verwerken_routes.size());
                            //for(int pad : alternatieve_route) {
                            //    System.out.println("ALTERNATIEVE_ROUTE " + pad);
                           // }
                            
                        } else {
                            ga_alternatieve = true;
                            // huidige route verder aanvullen.
                            huidige_route.add(huidige_stap_positie);
                            //start_vak = huidige_stap_positie;
                            // Controleer of dit toevallig de vriend is die je zoekt!
                            Vak eind_vak_huidige_route = doolhofMap.get(huidige_stap_positie);
                            if(eind_vak_huidige_route == eindVak){
                                // sla huidige_route op
                                System.out.println("route gevonden------------------------------------------------------");
                                LinkedList<Integer> route_gevonden = (LinkedList<Integer>) huidige_route.clone();
                                gevonden_routes.add(route_gevonden);
                                if(kortste_route.isEmpty()) {
                                    kortste_route = (LinkedList<Integer>) huidige_route.clone();
                                }
                                if(!kortste_route.isEmpty() && huidige_route.size() < kortste_route.size()) {
                                    kortste_route = (LinkedList<Integer>) huidige_route.clone();
                                }
                              //  nog_opties_teller = 0;
                                break;
                            }
                        }
                        // als huidige route al langer is dan huidig korste route, dan gelijk afbreken en een alternatieve route oppakken.

                        if(!kortste_route.isEmpty() && huidige_route.size() > kortste_route.size()) {
                            // huidige route heeft al meer stappen dan de huidige kortste, dan kappen!
                            nog_opties_teller = 0;
                            break;
                        }
                    }
                }                
            } // Dit is einde FOR!!!!
            // Alle richtingen zijn bepaald dus nu met huidige route verder.
            start_vak = huidige_route.getLast();
            System.out.println("uit de hele route bepaling afhankelijk van nog opties teller doorgaan, als 0 is dan stoppen : " + nog_opties_teller);
            System.out.println("is te verwerkenroutes 0 dan afbreken " + te_verwerken_routes.size());
            // Er zijn in de huidige route geen opties meer en de te verwerken routes zijn ook verwerkt!
            if(nog_opties_teller == 0 && te_verwerken_routes.size() == 0) {
                break;
            }
            // Als alleen op de huidige route geen opties meer zijn dan moet we een alternatieve route erbij pakken.
            if(nog_opties_teller == 0) {
                System.out.println("Er zijn geen opties meer in deze route");
                System.out.println("te verwerken routes " + te_verwerken_routes.size());
                huidige_route.clear();
                huidige_route.addAll(te_verwerken_routes.pop());
                System.out.println("huidige route formaat " + huidige_route.size());
                start_vak = huidige_route.getLast();
                //huidige_route.clear();
                //LinkedList<Integer> alternatieve_route = (LinkedList<Integer>) huidige_route.clone();
//                for(int pad : huidige_route){
//                    Vak tempvak = doolhofMap.get(pad);
//                    System.out.println("huidige_route na pop dus alternatieve route gepakt " + pad + "coords" + tempvak.toString() );
//                }
            }
 
        }
        
        System.out.println("te verwerken routes" + te_verwerken_routes.size());
       // for(int pad : huidige_route){
         //   System.out.println("huidige_route" + pad);
    //    }
       // for(int pad : alternatieve_route){
          //  System.out.println("laatste alternatieve route " + pad);
       // }
        System.out.println("gevonden_routes " + gevonden_routes.size());
        if(gevonden_routes.size() > 0) {
            System.out.println("kom ik hier?");
            huidige_route.clear();
            huidige_route.addAll(gevonden_routes.pop());
            System.out.println(huidige_route.size());
            for(int pad : huidige_route){
                System.out.println("huidige_route" + pad);
            }
        } else {
            System.out.println("Er zijn geen routes gevonden!");
        }
        System.out.println("Toon eind lijst");
             System.out.println(huidige_route.size());
        while(gevonden_routes.size() > 0) {
            System.out.println(huidige_route.size());
            huidige_route.clear();
            huidige_route.addAll(gevonden_routes.pop());
        }
        System.out.println("de kortste " + kortste_route.size());
//        Creeer route voor elke richting. Sla op in een queue (vertrek plek + historie)
//        Ga met Last in First out het vervolg stap.
//        Indien men 1 kant op kan gaan, dan die toevoegen aan huidige test route + historie gekoppeld aan deze route dat dat vakje al geweest is.
//        Indien er geen leeg vlak meer dan verwijder huidige route en historie en pak volgende route optie uit de lijst OF als vriend gevonden dan opslaan in potientele routes OF het vakje is al gebruikt in de huidige route!
//        Indien alle routes geweest zijn en er zijn geen afslagen meer dan bepalen welke korste route had.

        
    
    
    
    
    }
    
    private static boolean is_gebruikt_vak (int indexnummer) {
        for (int vak : huidige_route) {
            if(indexnummer == vak) {
                return true;
            }
        }
        return false;
    }
    // Let op richting bepaling staat ook in Speler!
    private static void vul_richtingen(int current_maze_size) {
        richtingen.add(1);
        richtingen.add(-1);
        richtingen.add(-current_maze_size);
        richtingen.add(current_maze_size);
    }
    
    /*public int positionchange(String richting, int current_maze_size) {
        int position_change_amount = 0;
        switch(richting) {
            case "right":
                position_change_amount = 1;
                break;
            case "left":
                position_change_amount = -1;
                break;
            case "up":
                position_change_amount = -current_maze_size;
                break;
            case "down":
                position_change_amount = current_maze_size;
                break;
        }        
        return position_change_amount;
    }*/
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