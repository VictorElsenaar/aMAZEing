package amazeing;

import java.awt.Color;
import java.util.Random;

/**
 * Class cheater. Dit object is op de speelmap een figuur dat je oppakt en er direct een random(5,15) aantal stappen van de spelers totale stappen afhaalt.
 * @author Victor Elsenaar en Kahoo Wu
 */
public class Cheater extends Figuur{
    private int waarde;
    
    /**
     * Nieuw instantie van dit object
     * @param vak_size_pixels = map afhankelijke maat van een vak
     * @param theme = het ingestelde theme
     */
    public Cheater(int vak_size_pixels, String theme) {
        super(Color.RED, vak_size_pixels, theme);       
        setWaarde();
        initialiseerImage("cheater");
    }
    
    public int getWaarde(){
        return this.waarde;
    }
    /**
     * Stelt een randomwaarde in tussen de 5 en 15
     */
    public void setWaarde() {
        waarde = getRandomNumber(5, 15);
    }
    
    /**
     * Genereert een random getal tussen de minimum- en de maximumwaarde.
     * @param min De minimumwaarde dat een random getal mag hebben.
     * @param max De maximumwaarde dat een random getal mag hebben. 
     * @return Een random getal.
     */
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
