package amazeing;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author vic
 */
public class AMAZEing {
    public static final String THEME = "mario"; // default, mario, minecraft
    public static boolean debug = true;
    
    /**
     * @param args the command line arguments
     */
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    
    public static void main(String[] args) {       
        Game game = new Game();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        game.setTitle("aMAZEing");
        game.setSize(WIDTH, HEIGHT);
        game.setResizable(false);
        
        while(true) 
        {
            try {
                game.executeQueue();
                game.updateStatistics();
                game.checkEndLevel();
            } catch (Exception ex) {  
            }
        }          
    }
}
