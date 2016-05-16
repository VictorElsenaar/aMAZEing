/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.awt.Color;

/**
 *
 * @author vic
 */
public class Speler extends Figuur {
    private int bazooka_count;
    public Speler() {
        super("speler", Color.BLUE);
        bazooka_count = 0;
    }
    public void addBazooka() {
        this.bazooka_count++;
    }
    public int getCount() {
        return bazooka_count;
    }
}
