package amazeing;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class MenuPanel extends JPanel{
    private JPanel menuPanel;
    private JComboBox<String> levelLijst;
    private JButton startButton;
    private JButton restartButton;
    private JButton afsluitenButton;
    private JButton goButton;
            
    //Constructor
    public MenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(110, 20));
        startButton.setBounds(10, 10, 110, 20);
        add(startButton);

        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(110, 20));
        restartButton.setBounds(10, 40, 110, 20);
        add(restartButton);

        afsluitenButton = new JButton("Afsluiten");
        afsluitenButton.setPreferredSize(new Dimension(110, 20));
        afsluitenButton.setBounds(10, 70, 110, 20);
        add(afsluitenButton);
        
        String[] levels = new String[] {
            "level 1", 
            "level 2",
            "level 3",
            "level 4"
        };
        levelLijst = new JComboBox<>(levels);
        levelLijst.setBounds(10, 100, 110, 20);
        add(levelLijst);
        
        goButton = new JButton("Go");
        goButton.setPreferredSize(new Dimension(110, 20));
        goButton.setBounds(10, 130, 110, 20);
        add(goButton);
    }
    public JButton getStartButton() {
        return startButton;
    }
    public JButton getRestartButton() {
        return restartButton;
    }
    public JButton getAfsluitenButton() {
        return afsluitenButton;
    }
    public JButton getGoButton() {
        return goButton;
    }
    public JComboBox<String> getLevelLijst() {
        return levelLijst;
    }
}
