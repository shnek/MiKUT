import OperatorResolver.NumberVeryficator;
import junit.framework.TestCase;
import org.junit.Assert.*;

/**
 * Created by kuba on 09.11.15.
 */
public class NumberVeryficatorTest extends TestCase{

    public void testPhoneNumbers(){
        NumberVeryficator nv = new NumberVeryficator();
        assertEquals("Heyah.", nv.verify("888888888"));
    }



}
