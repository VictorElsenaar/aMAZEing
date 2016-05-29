package amazeing;
import static amazeing.AMAZEing.debug;
import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kahoo
 */
public class Game extends JFrame{

    private boolean fireing = false;
    private Level level;
    private JPanel gamePanel;
    private MenuPanel menuPanel;
    
    private JPanel informationPanel;
    private JPanel shadow_informationPanel;
    private JPanel blackborder_informationPanel;
    
    private JPanel helperPanel;
    
    private JLabel infoLabel;
    
    /**
     * gameState
     * 0 = menu afhandeling
     * 1 = spelen
     * 8 = endlevel
     * 9 = endgame
     */
    private int gameState = 0;
    
    /**
     * Synchroon afhandelen van input van de speler door middel van een queue
     */
    private Queue<QueueHandler> queue = new LinkedList<QueueHandler>();
    
    
    public Game() {
        setLayout(null);
        setFocusTraversalKeysEnabled(false); // TAB disable
        
        helperPanel = new JPanel();
        helperPanel.setSize(520,520);
        helperPanel.setBounds(10,10,520,520);
        helperPanel.setLayout(null);
        helperPanel.setVisible(false);
        helperPanel.setBackground(Color.red);
        add(helperPanel);
        
        informationPanel = new JPanel();
        informationPanel.setSize(420, 32);
        informationPanel.setBounds(60,200, 420,32); //moet dus op basis van gamepanel zijn
        informationPanel.setBackground(Color.GREEN.brighter().brighter());
        infoLabel = new JLabel();
        informationPanel.add(infoLabel, BorderLayout.CENTER);        
        add(informationPanel);
        
        shadow_informationPanel = new JPanel();
        shadow_informationPanel.setSize(430, 42);
        shadow_informationPanel.setBounds(55,195, 430,42); //moet dus op basis van gamepanel zijn
        shadow_informationPanel.setBackground(Color.GREEN.darker());
        add(shadow_informationPanel);
        
        blackborder_informationPanel = new JPanel();
        blackborder_informationPanel.setSize(434,46);
        blackborder_informationPanel.setBounds(53,193, 434,46);
        blackborder_informationPanel.setBackground(Color.BLACK);
        add(blackborder_informationPanel);
        
        setInformationPanel(true);
                
        gamePanel = new JPanel();
        gamePanel.setSize(520, 520);
        gamePanel.setBounds(10, 10, 520, 520);
        Border border = new LineBorder(Color.lightGray, 2, true);
        gamePanel.setBorder(border);
        gamePanel.setLayout(null);
        gamePanel.setBackground(Color.WHITE);
        level = new Level(0);
        level.setBounds(10, 10, 501, 501);
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
                if(gameState == 1){
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
            } 
            @Override
            public void keyReleased(KeyEvent e) { 
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    if(debug){System.out.println("(F)IRE - pick a direction");}
                    fireing = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if(debug){System.out.println("(S)how optimal route");}
                    keyS();
                    
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(debug){System.out.println("Space pressed, only works on end level");}
                    if (gameState == 8) {
                        keySPACE();
                    }
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
                if(level.getSpelersVak() == level.getVriendVak()) {
                    setInformationPanel(true, "Vriend al gevonden! Druk op restart of kies een nieuw level.");
                } else {
                    gameState = 1;
                    setInformationPanel(false);
                    requestFocusInWindow();
                }
            }
            if (e.getActionCommand().equals("Restart")) {
                level.removeAll();
                level.setLevel(level.getCurrentLevel());
                gameState = 1;
                setInformationPanel(false);
                repaint();
                requestFocusInWindow();
            }
            if (e.getActionCommand().equals("Afsluiten")) {
                System.exit(0);
            }
            if (e.getActionCommand().equals("Go")) {
                JComboBox<String> levelLijst = menuPanel.getLevelLijst();
                String selectedLevel = (String) levelLijst.getSelectedItem();
                level.removeAll();
                switch (selectedLevel) {
                    case "level 1":
                        level.setLevel(0);
                        break;
                    case "level 2":
                        level.setLevel(1);
                        break;
                    case "level 3":
                        level.setLevel(2);
                        break;
                    case "level 4":
                        level.setLevel(3);
                        break;
                }
                repaint();
                gameState = 1;
                setInformationPanel(false);
                requestFocusInWindow();
            }
        }
    }
    private void keyS() {
        queue.add(new QueueHandler("None","optimal_route"));
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
    private void keySPACE() {
        level.removeAll();
        this.level.setNextLevel();        
        gameState = 1;
        setInformationPanel(false);
        repaint();
        requestFocusInWindow();            
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
        level.action(next.getDirection(),next.getType());    
        if(debug){System.out.println("@@@@@"+next.getDirection());}
    }
    public void checkEndLevel() {
        if (level.getVriendVak() == level.getSpelersVak()) {
            if(debug){System.out.println("Vriend gevonden");}
            setInformationPanel(true, "Gefeliciteerd! Vriend gevonden.");   
           // menuPanel.requestFocusInWindow();
            setgameState(8);
            System.out.println("currentlevel " + level.getCurrentLevel() + " == " + level.getLevelsSize() + " level.getLevelsSize()");
            if(level.getCurrentLevel()+1 == level.getLevelsSize()) {
                System.out.println("Einde game, laatste map gespeeld, WELLICHT NOG AANPASSEN NAAR beter bericht");
                setgameState(9);
            }
        }
    }
    public void setgameState(int state) {
        this.gameState = state;
    }
    public int getgameState(){
        return this.gameState;
    }
    
    public void setInformationPanel(boolean b, String text) {
        infoLabel.setText(text);
        setCombinedInformationPanel(b);
    }
    public void setInformationPanel(boolean b) {
        infoLabel.setText("Druk op start om het spel te starten.");
        setCombinedInformationPanel(b);
    }
    public void setCombinedInformationPanel(boolean b) {
        informationPanel.setVisible(b);
        shadow_informationPanel.setVisible(b);
        blackborder_informationPanel.setVisible(b);
    }
}
