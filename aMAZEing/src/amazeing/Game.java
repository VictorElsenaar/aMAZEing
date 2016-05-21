package amazeing;
import static amazeing.AMAZEing.debug;
import com.sun.org.apache.bcel.internal.Repository;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import sun.management.Agent;

/**
 *
 * @author Kahoo
 */
public class Game extends JFrame{

    boolean fireing = false;
    private Level level;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JButton startButton;
    
    public Game() {
        setLayout(null);
        
        gamePanel = new JPanel();
        gamePanel.setSize(520, 520);
        gamePanel.setBounds(10, 10, 520, 520);
        Border border = new LineBorder(Color.lightGray, 2, true);
        gamePanel.setBorder(border);
        gamePanel.setLayout(null);
        level = new Level();
        level.setBounds(10, 10, 500, 500);
        gamePanel.add(level);
        add(gamePanel);
        
//        JPanel menuPanel = new MenuPanel();
//        menuPanel.setSize(130, 520);
//        menuPanel.setBounds(540, 10, 130, 520);
//        menuPanel.setBorder(border);
//        add(menuPanel);
        
        menuPanel = new JPanel();
        menuPanel.setSize(130, 520);
        menuPanel.setBounds(540, 10, 130, 520);
        menuPanel.setBorder(border);
        menuPanel.setLayout(null);
        
        ActionListener listener = new ClickListener();
        
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(110, 20));
        startButton.setBounds(10, 10, 110, 20);
        startButton.addActionListener(listener);
        menuPanel.add(startButton);
        
        JButton restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(110, 20));
        restartButton.setBounds(10, 40, 110, 20);
        restartButton.addActionListener(listener);
        menuPanel.add(restartButton);
        
        JButton afsluitenButton = new JButton("Afsluiten");
        afsluitenButton.setPreferredSize(new Dimension(110, 20));
        afsluitenButton.setBounds(10, 70, 110, 20);
        afsluitenButton.addActionListener(listener);
        menuPanel.add(afsluitenButton);
        
        add(menuPanel);
        
        addKeyListener(new KeyListener() { 
            public void keyPressed(KeyEvent e) { } 
            @Override
            public void keyReleased(KeyEvent e) { 
                if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                   if(debug){System.out.println("DOWN");}
                   if(fireing){
                       if(debug) {System.out.println("FIRE DOWN"); }
                       level.action("down", "fire");
                       fireing = false;
                   } else { // move character
                       level.action("down", "move");

                   }
                } 
                if (e.getKeyCode() == KeyEvent.VK_UP ) {
                   if(debug){System.out.println("UP");}
                   if(fireing){
                       if(debug) {System.out.println("FIRE UP"); }
                       level.action("up", "fire");
                       fireing = false;
                   } else { // move character                   
                       level.action("up", "move");
                   }
                } 
                if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                   if(debug){System.out.println("RIGHT");}
                    if(fireing){
                       if(debug) {System.out.println("FIRE RIGHT"); }
                       level.action("right", "fire");
                       fireing = false;
                   } else { // move character
                        level.action("right", "move");
                   }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                   if(debug){System.out.println("LEFT");}
                   if(fireing){
                       if(debug) {System.out.println("FIRE LEFT"); }
                       level.action("left", "fire");
                       fireing = false;
                   } else { // move character                   
                       level.action("left", "move");
                   }
                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    if(debug){System.out.println("(F)IRE - pick a direction");}
                    fireing = true;
                }
            } 
            public void keyTyped(KeyEvent e) { } 
        }); 
       
      // setLevel(levelOne());
      // if(debug){readLevel();} // controleer het level
    }
    
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (debug){System.out.println(e.getActionCommand());}
            if (e.getActionCommand().equals("Start")) {
                
                requestFocusInWindow();
            }
            if (e.getActionCommand().equals("Restart")) {
                level.setLevel(level.getCurrentLevel());
                repaint();
            }
            if (e.getActionCommand().equals("Afsluiten")) {
                System.exit(0);
            }
        }
    }
//    public void tekenLevel() {
//        Muur borderMuur = new Muur();
//        Figuur empty = new Empty();
//        borderMuur.setBorderMuur(true);
//        map = new HashMap<>();
//        for(int x = 0; x < AANTALVAKKENBREEDTE; x++) {
//            for(int y = 0; y < AANTALVAKKENHOOGTE; y++) {
//                if (x == 0 || x == (AANTALVAKKENBREEDTE-1) || y == 0 || y == (AANTALVAKKENHOOGTE-1)) {
//                    map.put(new Vak(x,y),borderMuur);
//                } else {
//                    map.put(new Vak(x,y),empty);
//                }
//            }
//        }
//    }
/*    public void setLevel(String level) {
        System.out.println(level);

        Muur muur = new Muur();
        Figuur empty = new Empty();
        map = new HashMap<>();
        
        int counter = 0;
        for (int x = 0; x < ROWS ; x++) {
            if(debug){System.out.println("rows " + x);}
            for(int y = 0; y < COLUMNS ; y++) {
                if(debug){System.out.println("columns " + y);}
                String typeOnPosition = level.substring(counter, counter+1);
                if(debug){System.out.println(typeOnPosition);}
                if(Integer.parseInt(typeOnPosition) == 1) {
                     map.put(new Vak(y,x),muur);
                   // map[x][y] = new Tile(new Figure("Muur"));
                } else if (Integer.parseInt(typeOnPosition) == 2) {
                   // map[x][y] = new Tile(new Figure("Speler"));
                } else
                {
                    map.put(new Vak(y,x),empty);
                   // map[x][y] = new Tile(new Figure("Leeg"));
                }
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
        for (Map.Entry<Vak,Figuur> entry : map.entrySet()) {
            System.out.println(entry.getKey().getCord() + "/" + entry.getValue().getNaam());
        }
    }
    
    public void paint(Graphics g) {
        for (int i = 0; i < levelsize; i+=VAKGROOTTE) {
            for (int j = 0; j < levelsize; j+=VAKGROOTTE) {
              g.drawLine(i, j, levelsize, j); // de grid tekenen
              g.drawLine(i, j, i, levelsize); // de grid tekenen
            }
        }
        
        Set<Vak> keySet = map.keySet();
        for (Vak key : keySet)
        {
            Figuur value = map.get(key);
            if (value.getNaam().equals("muur")) { // als object een muur is
                
                g.fillRect((key.getXAs()*VAKGROOTTE), (key.getYAs()*VAKGROOTTE), VAKGROOTTE, VAKGROOTTE); // vul de vakken zwart
            }
        }
    }
        
    
    public String levelOne() {
      return  "1111111111"
            + "1010100001"
            + "1010101011"
            + "1010101001"
            + "1010001101"
            + "1010110001"
            + "1010100111"
            + "1010101101"
            + "1000100001"
            + "1111111111";
    }    */
}
