package amazeing;

import static amazeing.AMAZEing.debug;
import static amazeing.AMAZEing.THEME;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class OptimaleRouteTest {
    
    public OptimaleRouteTest() {
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
     * Test of vindRoute method, of class OptimaleRoute.
     */
    @Test
    public void testVindRoute() {
        System.out.println("vindRoute in level 4 (3 is indexnr) vinden met minimaal 100 stappen");
        Level level_instance = new Level(3);
        ArrayList<Vak> doolhofMap = level_instance.getcurrentMap();        
        OptimaleRoute instance = new OptimaleRoute(level_instance.getVak_size_pixels(), THEME, doolhofMap, level_instance.getMazeSize(), level_instance.getSpelersVak(), level_instance.getVriendVak());
        LinkedList<Integer> result = instance.vindRoute();
        
        int final_result = result.size();
        int expected_result = 100;
        boolean b = false;
        if(final_result > expected_result) {
            b = true;
        }
        assertTrue(b);
    }

    /**
     * Test of paint method, of class OptimaleRoute.
     */
//    @Test
//    public void testPaint() {
//        System.out.println("paint");
//        Graphics g = null;
//        OptimaleRoute instance = null;
//        instance.paint(g);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of initialiseerImage method, of class OptimaleRoute.
     */
//    @Test
//    public void testInitialiseerImage() {
//        System.out.println("initialiseerImage");
//        OptimaleRoute instance = null;
//        instance.initialiseerImage();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of run method, of class OptimaleRoute.
     */
//    @Test
//    public void testRun() {
//        System.out.println("run");
//        OptimaleRoute instance = null;
//        instance.run();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
