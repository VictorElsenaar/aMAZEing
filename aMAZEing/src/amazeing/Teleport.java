/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.awt.Color;

/**
 *
 * @author vfelsenaar
 */
public class Teleport extends Figuur{
    private int locationIndex;
    private Teleport other;

    public Teleport(int vak_size_pixels, String theme) {
        super(Color.LIGHT_GRAY, vak_size_pixels, theme);
        initialiseerImage("teleport");
    }
    public void setLocationIndex(int index) {
        locationIndex = index;
    }
    public int getLocationIndex() {
        return locationIndex;
    }
    public void setOther(Teleport other) {
        this.other = other;
    }
    public Teleport getOther() {
        return other;
    }
    
}
