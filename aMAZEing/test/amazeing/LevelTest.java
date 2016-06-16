package amazeing;

import static amazeing.AMAZEing.debug;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Elsenaar en Kahoo Wu
 */
public class LevelTest {
    
    public LevelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        debug = false;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setNextLevel method, of class Level.
     */
    @Test
    public void testSetNextLevel1() {
        System.out.println("setNextLevel 0 + 1");
        Level instance = new Level(0);
        instance.setNextLevel();
        int expResult = 1;
        int result = instance.getCurrentLevel();
        assertEquals(expResult, result);        
    }
    /**
     * Test of setNextLevel method, of class Level.
     */
    @Test
    public void testSetNextLevel2() {
        System.out.println("setNextLevel Max available level + 1");
        Level instance = new Level(3);
        instance.setNextLevel();
        int expResult = 3;
        int result = instance.getCurrentLevel();
        assertEquals(expResult, result);        
    }    
    /**
     * Test of action method, of class Level.
     */
    @Test
    public void testActionM_D() {
        System.out.println("Action move down level 1 (arraylist indexnr 0) possible");
        String direction = "down";
        String type = "move";
        Level instance = new Level(0);
        Vak oudePositie = instance.getSpelersVak();
        instance.action(direction, type);
        Vak nieuwePositie = instance.getSpelersVak();
        //assertnotEquals(expResult, result);
        assertNotSame(oudePositie, nieuwePositie);
    }
    @Test
    public void testActionM_L() {
        System.out.println("Action move left level 1 (arraylist indexnr 0) not possible");
        String direction = "left";
        String type = "move";
        Level instance = new Level(0);
        Vak oudePositie = instance.getSpelersVak();
        instance.action(direction, type);
        Vak nieuwePositie = instance.getSpelersVak();
        //assertnotEquals(expResult, result);
        assertSame(oudePositie, nieuwePositie);
    }
    @Test
    public void testActionF_R() {
        System.out.println("Action fire right level 1 (arraylist indexnr 0) possible with ammo");
        String direction = "right";
        String type = "fire";
        Level instance = new Level(0);
        Speler speler = (Speler) instance.getSpelersVak().getFiguur();
        speler.addKogels();
        ArrayList<Vak> pre_doolhofmap = instance.getcurrentMap();
        int tempindex = pre_doolhofmap.indexOf(instance.getSpelersVak());
        Vak schietvak = (Vak) pre_doolhofmap.get(tempindex+1);
        Figuur figuurvoor = (Figuur) schietvak.getFiguur();
        instance.action(direction, type);
        ArrayList<Vak> post_doolhofmap = instance.getcurrentMap();
        Vak eindschietvak = (Vak) pre_doolhofmap.get(tempindex+1); // +1 is naar rechts
        Figuur figuurna = (Figuur) eindschietvak.getFiguur();
        assertNotSame(figuurvoor, figuurna);

    }
    @Test
    public void testActionF_RnoAmmo() {
        System.out.println("Action fire right level 1 (arraylist indexnr 0) possible without ammo");
        String direction = "right";
        String type = "fire";
        Level instance = new Level(0);
        Speler speler = (Speler) instance.getSpelersVak().getFiguur();
        ArrayList<Vak> pre_doolhofmap = instance.getcurrentMap();
        int tempindex = pre_doolhofmap.indexOf(instance.getSpelersVak());
        Vak schietvak = (Vak) pre_doolhofmap.get(tempindex+1);
        Figuur figuurvoor = (Figuur) schietvak.getFiguur();
        instance.action(direction, type);
        ArrayList<Vak> post_doolhofmap = instance.getcurrentMap();
        Vak eindschietvak = (Vak) pre_doolhofmap.get(tempindex+1); // +1 is naar rechts
        Figuur figuurna = (Figuur) eindschietvak.getFiguur();
        assertSame(figuurvoor, figuurna);

    }    
    @Test
    public void testActionF_L() {
        System.out.println("Action fire left level 1 (arraylist indexnr 0) not possible (buitenmuur) with ammo");
        String direction = "left";
        String type = "fire";
        Level instance = new Level(0);
        Speler speler = (Speler) instance.getSpelersVak().getFiguur();
        speler.addKogels();
        ArrayList<Vak> pre_doolhofmap = instance.getcurrentMap();
        int tempindex = pre_doolhofmap.indexOf(instance.getSpelersVak());
        Vak schietvak = (Vak) pre_doolhofmap.get(tempindex-1);
        Figuur figuurvoor = (Figuur) schietvak.getFiguur();
        instance.action(direction, type);
        ArrayList<Vak> post_doolhofmap = instance.getcurrentMap();
        Vak eindschietvak = (Vak) pre_doolhofmap.get(tempindex-1); // -1 is naar links
        Figuur figuurna = (Figuur) eindschietvak.getFiguur();
        assertSame(figuurvoor, figuurna);
    }    

    /**
     * Test of getSpelersVak method, of class Level.
     */
    @Test
    public void testGetSpelersVak() {
        System.out.println("getSpelersVak");
        Level instance = new Level(0);
        Vak result = instance.getSpelersVak();
        if(result.getFiguur() instanceof Speler) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Test of getVriendVak method, of class Level.
     */
    @Test
    public void testGetVriendVak() {
        System.out.println("getVriendVak");
        Level instance = new Level(0);
        Vak result = instance.getVriendVak();
        if(result.getFiguur() instanceof Vriend) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Test of getVijandsVak method, of class Level.
     */
    @Test
    public void testGetVijandsVak() {
        System.out.println("getVijandsVak in level 4(index 3)");
        Level instance = new Level(3);
        Vak result = instance.getVijandsVak();
        if(result.getFiguur() instanceof Vijand) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }    
    /**
     * Test of toonOptimaleRoute method, of class Level.
     */
//    @Test
//    public void testToonOptimaleRoute() {
//        System.out.println("toonOptimaleRoute");
//        Level instance = null;
//        instance.toonOptimaleRoute();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of vijandBeweeg method, of class Level.
     */
//    @Test
//    public void testVijandBeweeg() {
//        System.out.println("vijandBeweeg");
//        Level instance = null;
//        instance.vijandBeweeg();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of startVijand method, of class Level.
     */
//    @Test
//    public void testStartVijand() {
//        System.out.println("startVijand");
//        Level instance = null;
//        instance.startVijand();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of stopVijand method, of class Level.
     */
//    @Test
//    public void testStopVijand() {
//        System.out.println("stopVijand");
//        Level instance = null;
//        instance.stopVijand();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getcurrentMap method, of class Level.
     */
//    @Test
//    public void testGetcurrentMap() {
//        System.out.println("getcurrentMap");
//        Level instance = null;
//        ArrayList<Vak> expResult = null;
//        ArrayList<Vak> result = instance.getcurrentMap();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getMazeSize method, of class Level.
     */
//    @Test
//    public void testGetMazeSize() {
//        System.out.println("getMazeSize");
//        Level instance = null;
//        int expResult = 0;
//        int result = instance.getMazeSize();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


    /**
     * Test of getCurrentLevel method, of class Level.
     */
//    @Test
//    public void testGetCurrentLevel() {
//        System.out.println("getCurrentLevel");
//        Level instance = null;
//        int expResult = 0;
//        int result = instance.getCurrentLevel();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getLevelsSize method, of class Level.
     */
//    @Test
//    public void testGetLevelsSize() {
//        System.out.println("getLevelsSize");
//        Level instance = null;
//        int expResult = 0;
//        int result = instance.getLevelsSize();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getLevel method, of class Level.
     */
//    @Test
//    public void testGetLevel() {
//        System.out.println("getLevel");
//        int nr = 0;
//        Level instance = null;
//        String expResult = "";
//        String result = instance.getLevel(nr);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of setCurrentLevel method, of class Level.
     */
//    @Test
//    public void testSetCurrentLevel() {
//        System.out.println("setCurrentLevel");
//        int currentLevel = 0;
//        Level instance = null;
//        instance.setCurrentLevel(currentLevel);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of setLevel method, of class Level.
     */
//    @Test
//    public void testSetLevel() {
//        System.out.println("setLevel");
//        int nr = 0;
//        Level instance = null;
//        instance.setLevel(nr);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addLevels method, of class Level.
     */
//    @Test
//    public void testAddLevels() {
//        System.out.println("addLevels");
//        Level instance = null;
//        instance.addLevels();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of readLevel method, of class Level.
     */
//    @Test
//    public void testReadLevel() {
//        System.out.println("readLevel");
//        Level instance = null;
//        instance.readLevel();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getStappen method, of class Level.
     */
//    @Test
//    public void testGetStappen() {
//        System.out.println("getStappen");
//        Level instance = null;
//        int expResult = 0;
//        int result = instance.getStappen();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getKogels method, of class Level.
     */
//    @Test
//    public void testGetKogels() {
//        System.out.println("getKogels");
//        Level instance = null;
//        int expResult = 0;
//        int result = instance.getKogels();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getHelper method, of class Level.
     */
//    @Test
//    public void testGetHelper() {
//        System.out.println("getHelper");
//        Level instance = null;
//        int expResult = 0;
//        int result = instance.getHelper();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }    
}
