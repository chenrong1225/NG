package testN.NG;

//import com.beust.testng.*;
import org.testng.TestNG;

import static org.testng.Assert.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestNG
{
    /**
     * Create the test case
     *
     * @param class1 name of the test case
     */
    public AppTest( Class<AppTest> class1 )
    {
        super( );
    }

    /**
     * @return the suite of tests being tested
     */
    public static AppTest suite()
    {
        return new AppTest( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    public void test()
    {
    	System.out.println("ddddd");
    }
}
