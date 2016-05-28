package amazeing;

import javax.swing.JFrame;

/**
 *
 * @author vic
 */
public class AMAZEing {

    public static boolean debug = true;
    
    /**
     * @param args the command line arguments
     */
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    //public static Speler speler;
    private static Thread thread = new Thread();
    
    public static void main(String[] args) {
        // Teken globale panel
        // midden links is het level gebied (toont een dummy plaatje van een doolhof
        // rechts is het menu
        
        // Bij keuze starten van level
        // bouw in het level gebied een level op
        
        Game game = new Game();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        game.setTitle("aMAZEing");
        game.setSize(WIDTH, HEIGHT);
        game.setResizable(false);
        
        thread.start();
        while(true) 
        {
            try {
                //Thread.sleep(25);
                game.executeQueue();
            } catch (Exception ex) {  
                // do something with the exception
            }
        }                           
    
    }
}
