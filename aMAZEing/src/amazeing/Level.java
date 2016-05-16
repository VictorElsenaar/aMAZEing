package amazeing;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author Kahoo
 */
public class Level extends JFrame{
    private final int BREEDTE = 500;
    private final int HOOGTE = 500;
    private final int VAKBREEDTE = 25;
    private final int VAKHOOGTE = 25;
    private final int AANTALVAKKENBREEDTE = 20;
    private final int AANTALVAKKENHOOGTE = 20;
    
    Map<Vak,Figuur> map;
    
    public Level() {
        setSize(BREEDTE, HOOGTE);
    }
    
    public void tekenLevel() {
        Muur borderMuur = new Muur();
        Figuur empty = new Empty();
        borderMuur.setBorderMuur(true);
        map = new HashMap<>();
        for(int x = 0; x < AANTALVAKKENBREEDTE; x++) {
            for(int y = 0; y < AANTALVAKKENHOOGTE; y++) {
                if (x == 0 || x == (AANTALVAKKENBREEDTE-1) || y == 0 || y == (AANTALVAKKENHOOGTE-1)) {
                    map.put(new Vak(x,y),borderMuur);
                } else {
                    map.put(new Vak(x,y),empty);
                }
            }
        }
    }
    
    public void test() {
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
        for (int i = 0; i < BREEDTE; i+=VAKBREEDTE) {
            for (int j = 0; j < HOOGTE; j+=VAKHOOGTE) {
              g.drawLine(i, j, BREEDTE, j); // de grid tekenen
              g.drawLine(i, j, i, HOOGTE); // de grid tekenen
            }
        }
        
        Set<Vak> keySet = map.keySet();
        for (Vak key : keySet)
        {
            Figuur value = map.get(key);
            if (value.getNaam().equals("muur")) { // als object een muur is
                
                g.fillRect((key.getXAs()*VAKBREEDTE), (key.getYAs()*VAKHOOGTE), VAKBREEDTE, VAKHOOGTE); // vul de vakken zwart
            }
        }
    }
}
