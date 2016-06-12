package amazeing;

import javax.swing.JFrame;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class AMAZEing {
    // Globale variabelen
    /**
     * THEME is bepalend welke plaatjes er geladen worden. 
     * Noodzakelijk in Netbeans is dat bij een wisseling van het THEME dat er eerst een 'Clean and Build Project' gedaan wordt.
     */
    public static final String THEME = "minecraft"; // default, mario, minecraft
    
    /**
     * debug true geeft alle logging weer. Met uitzondering van Optimale Route, deze heeft in zijn eigen klasse nog een extra optdebug variabele
     */
    public static boolean debug = true;
    
    /**
     * Formaat van het spel. Do not change!
     */
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    
    public static void main(String[] args) {       
        // Start het spel
        Game game = new Game();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        game.setTitle("aMAZEing");
        game.setSize(WIDTH, HEIGHT);
        game.setResizable(false);
        
        // Constante gebruikers interactie afhandeling
        // Updaten van spel statistieken en controle op einde level
        while(true) 
        {
            try {
                game.updateStatistics();
                game.executeQueue();
                game.checkEndLevel();
            } catch (Exception ex) {  
            }
        }          
    }
}
