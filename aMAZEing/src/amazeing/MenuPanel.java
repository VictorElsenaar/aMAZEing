package amazeing;

import static amazeing.AMAZEing.debug;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Kahoo
 */
public class MenuPanel extends JPanel{
    private JPanel menuPanel;
    public MenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(null);

        ActionListener listener = new ClickListener();

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(110, 20));
        startButton.setBounds(10, 10, 110, 20);
        startButton.addActionListener(listener);
        add(startButton);

        JButton restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(110, 20));
        restartButton.setBounds(10, 40, 110, 20);
        restartButton.addActionListener(listener);
        add(restartButton);

        JButton afsluitenButton = new JButton("Afsluiten");
        afsluitenButton.setPreferredSize(new Dimension(110, 20));
        afsluitenButton.setBounds(10, 70, 110, 20);
        afsluitenButton.addActionListener(listener);
        add(afsluitenButton);
        System.out.println("asdasd");
    }
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (debug){System.out.println(e.getActionCommand());}
            if (e.getActionCommand().equals("Start")) {
                requestFocusInWindow();
            }
            if (e.getActionCommand().equals("Restart")) {
            }
            if (e.getActionCommand().equals("Afsluiten")) {
                System.exit(0);
            }
        }
    }
    
    
}
