package amazeing;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Class Vak bevat de details van het vak. X y en weet welk figuur erop staat.
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Vak extends JComponent{
    private Figuur figuur;
    private int x;
    private int y;
    private int vak_size_pixels;
    private JPanel panel;
    
    /**
     * Nieuw instantie van vak
     * @param x de verticale cordinaat van het vak
     * @param y de horizontale cordinaat van het vak
     * @param figuur = het figuur dat op deze vak staat
     */
    public Vak(int x, int y, Figuur figuur) {
        this.x = x;
        this.y = y;
        this.figuur = figuur;
        initPanel();
        add(panel);
    }
    
    public boolean isBazooka(Vak vak) {
        return vak.getFiguur() instanceof Bazooka;
    }
    public boolean isMuur(Vak vak){
        return vak.getFiguur() instanceof Muur;
    }
    public boolean isVriend(Vak vak) {
        return vak.getFiguur() instanceof Vriend;
    }
    public boolean isHelper(Vak vak) {
        return vak.getFiguur() instanceof Helper;
    }
    public boolean isCheater(Vak vak) {
        return vak.getFiguur() instanceof Cheater;
    }
    public boolean isTeleport(Vak vak) {
        return vak.getFiguur() instanceof Teleport;
    }
    public boolean isVijand(Vak vak) {
        return vak.getFiguur() instanceof Vijand;
    }

    /**
     * Leest de cordinaten van een vak (debug doeleinde)
     * @return cordinaten van vak
     */
    public String toString() {
        String x = Integer.toString(getx());
        String y = Integer.toString(gety());
        String cord = x + " " + y + " bevat " + figuur.toString();
        return cord;
    }
    
    public Figuur getFiguur(){
        return figuur;
    }
    public void setFiguur(Figuur figuur){
        this.figuur = figuur;
    }
    public int getx() {
        return x;
    }
    public int gety() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setVakSizePixels(int vak_size_pixels) {
        this.vak_size_pixels = vak_size_pixels;
    }
    /**
     * Initieer standaard paneel met vak_size_pixel maat van het object zelf.
     */
    public void initPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(vak_size_pixels, vak_size_pixels); 
    }
    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
