package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kahoo
 */
public class MenuPanel extends JPanel{
    private JPanel menuPanel;
    private JComboBox<String> levelLijst;
    private JButton startButton;
    private JButton restartButton;
    private JButton afsluitenButton;
    private JButton goButton;
            
    public MenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);

//        ActionListener listener = new ClickListener();

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(110, 20));
        startButton.setBounds(10, 10, 110, 20);
        //startButton.addActionListener(listener);
        add(startButton);

        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(110, 20));
        restartButton.setBounds(10, 40, 110, 20);
        //restartButton.addActionListener(listener);
        add(restartButton);

        afsluitenButton = new JButton("Afsluiten");
        afsluitenButton.setPreferredSize(new Dimension(110, 20));
        afsluitenButton.setBounds(10, 70, 110, 20);
        //afsluitenButton.addActionListener(listener);
        add(afsluitenButton);
        
        String[] levels = new String[] {
            "level 1", 
            "level 2",
            "level 3"
        };
        levelLijst = new JComboBox<>(levels);
        levelLijst.setBounds(10, 100, 110, 20);
        add(levelLijst);
        
        goButton = new JButton("Go");
        goButton.setPreferredSize(new Dimension(110, 20));
        goButton.setBounds(10, 130, 110, 20);
//        goButton.addActionListener(listener);
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
