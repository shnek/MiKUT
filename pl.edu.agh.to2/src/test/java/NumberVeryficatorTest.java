import OperatorResolver.NumberVeryficator;
import junit.framework.TestCase;
import org.junit.Assert.*;

/**
 * Created by kuba on 09.11.15.
 */
public class NumberVeryficatorTest extends TestCase{

    public void testHeyahVerify(){
        NumberVeryficator nv = new NumberVeryficator();
        assertEquals("Heyah", nv.verify("888888888"));
    }
    public void testTmobileVerify(){
        NumberVeryficator nv = new NumberVeryficator();
        assertEquals("PTC / T-Mobile", nv.verify("608800000"));
    }
    public void testPlayVerify(){
        NumberVeryficator nv = new NumberVeryficator();
        assertEquals("P4", nv.verify("792600000"));

    }
}
