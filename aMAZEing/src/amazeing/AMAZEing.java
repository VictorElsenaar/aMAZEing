package amazeing;

import javax.swing.JFrame;

/**
 *
 * @author vic
 */
public class AMAZEing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Level level = new Level();
        level.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        level.setVisible(true);
        level.setTitle("aMAZEing level 1");
        level.setSize(600, 600);
        level.tekenLevel();
        level.test();
    }
    
}
