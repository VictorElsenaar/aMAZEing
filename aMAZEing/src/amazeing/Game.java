package amazeing;
import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kahoo
 */
public class Game extends JFrame{

    boolean fireing = false;
    private Level level;
    private JPanel gamePanel;
    private MenuPanel menuPanel;

    
    // Bouw Queue
    
    // Vul queue met acties vanuit Game (want keylistener)
    
    // Timer checkt queue voor nieuwe actie of bestaande actie
    
    // Actie > Level > speler > level > game > queue method (ik ben klaar)
    private Queue<QueueHandler> queue = new LinkedList<QueueHandler>();
    
    
    public Game() {
        setLayout(null);
        setFocusTraversalKeysEnabled(false); // TAB disable
        
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
        
        menuPanel = new MenuPanel();
        menuPanel.setSize(130, 520);
        menuPanel.setBounds(540, 10, 130, 520);
        menuPanel.setBorder(border);
        setListenersAanButtons();
        add(menuPanel);
        
        addKeyListener(new KeyListener() { 
            @Override
            public void keyPressed(KeyEvent e) { 
                if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                    keyLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                    keyRight();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
                    keyDown();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP ) {
                    keyUp();
                }                 
            } 
            @Override
            public void keyReleased(KeyEvent e) { 
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    if(debug){System.out.println("(F)IRE - pick a direction");}
                    fireing = true;
                }
            } 
            public void keyTyped(KeyEvent e) { } 
        }); 
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
                requestFocusInWindow();
            }
            if (e.getActionCommand().equals("Afsluiten")) {
                System.exit(0);
            }
            if (e.getActionCommand().equals("Go")) {
                JComboBox<String> levelLijst = menuPanel.getLevelLijst();
                String selectedLevel = (String) levelLijst.getSelectedItem();
                switch (selectedLevel) {
                    case "level 1":
                        level.setLevel(level.levelOne());
                        repaint();
                        requestFocusInWindow();
                        break;
                    case "level 2":
                        level.setLevel(level.levelTwo());
                        repaint();
                        requestFocusInWindow();
                        break;
                    case "level 3":
                        level.setLevel(level.levelThree());
                        repaint();
                        requestFocusInWindow();
                        break;
                }
            }
        }
    }
    private void keyLeft() {
        if(debug){System.out.println("LEFT");}
        if(fireing){
            if(debug) {System.out.println("FIRE LEFT"); }
            queue.add(new QueueHandler("left", "fire"));
            fireing = false;
        } else { // move character       
            queue.add(new QueueHandler("left", "move"));
        }            
    }
    private void keyRight() {
        if(debug){System.out.println("RIGHT");}
         if(fireing){
            if(debug) {System.out.println("FIRE RIGHT"); }
            queue.add(new QueueHandler("right", "fire"));
            fireing = false;
        } else { // move character
            queue.add(new QueueHandler("right", "move"));
        }        
    }
    private void keyDown() {
        if(debug){System.out.println("DOWN");}
        if(fireing){
            if(debug) {System.out.println("FIRE DOWN"); }
            queue.add(new QueueHandler("down", "fire"));
            fireing = false;
        } else { // move character
            queue.add(new QueueHandler("down", "move"));
        }        
    }
    private void keyUp() {
        if(debug){System.out.println("UP");}
        if(fireing){
            if(debug) {System.out.println("FIRE UP"); }
            queue.add(new QueueHandler("up", "fire"));
            fireing = false;
        } else { // move character        
            queue.add(new QueueHandler("up", "move"));
        }        
    }
    
    public void setListenersAanButtons() {
        ActionListener listener = new ClickListener();
        JButton startButton = menuPanel.getStartButton();
        startButton.addActionListener(listener);
        JButton restartButton = menuPanel.getRestartButton();
        restartButton.addActionListener(listener);
        JButton afsluitenButton = menuPanel.getAfsluitenButton();
        afsluitenButton.addActionListener(listener);
        JButton goButton = menuPanel.getGoButton();
        goButton.addActionListener(listener);
    }
    public void executeQueue() {
        QueueHandler next = queue.remove();
        System.out.println(next);
        level.action(next.getType(),next.getDirection());
    }
}
