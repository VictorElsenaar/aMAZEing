/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeing;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vic
 */
public class SpelerTest {
    
    public SpelerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of gebruikCheater method, of class Speler.
     */
    @Test
    public void testGebruikCheater_checkZero() {
        System.out.println("testGebruikCheater met nog maar 1 stap gezet");
        int dummyint = 5;
        String dummystring = "nvt";
        Speler speler = new Speler(dummyint,dummystring);
        Cheater cheater = new Cheater(dummyint,dummystring);
        speler.addaantalStappen();
        cheater.setWaarde(); // is altijd tussen de 5 en 15
        speler.gebruikCheater(cheater);
        
        int expResult = 0;
        int result = speler.getaantalStappen();
        assertEquals(expResult, result);        
    }
    /**
     * Test of gebruikCheater method, of class Speler.
     */
    @Test
    public void testGebruikCheater_Normal() {
        System.out.println("testGebruikCheater met 20 stappen gezet");
        int dummyint = 5;
        String dummystring = "nvt";
        Speler speler = new Speler(dummyint,dummystring);
        Cheater cheater = new Cheater(dummyint,dummystring);
        for (int i = 0; i < 20; i++) {
            speler.addaantalStappen();
        }
        cheater.setWaarde(); // is altijd tussen de 5 en 15
        int expResult = speler.getaantalStappen() - cheater.getWaarde();
        
        speler.gebruikCheater(cheater);
                
        int result = speler.getaantalStappen();
        assertEquals(expResult, result);        
    }   
}
/*
//                if(aantalStappen-cheater.getWaarde() < 0) {
//                    aantalStappen = 0;
//                } else {
//                    aantalStappen -= cheater.getWaarde();
*/