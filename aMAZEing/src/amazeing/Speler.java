/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

/**
 *
 * @author vic
 */
public class Speler extends Figuur {
    private int x;
    private int y;

    public Speler() {
        super("speler");
    }
    public void setx(int x) {
        this.x = x;
    }
    public void sety(int y) {
        this.y = y;
    }
    public int getx() {
        return x;
    }
    public int gety() {
        return y;
    }
}
