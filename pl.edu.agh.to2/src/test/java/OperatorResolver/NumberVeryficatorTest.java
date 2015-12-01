package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import junit.framework.TestCase;

/**
 * Created by kuba on 09.11.15.
 */
public class NumberVeryficatorTest extends TestCase{

    public void testPlayVerify(){
        OperatorResolver.veryficator.NumberVeryficator nv = new OperatorResolver.veryficator.NumberVeryficator();
        assertEquals(Operator.PLAY, nv.verify("792600000"));
    }
    public void testHeyahVerify(){
        OperatorResolver.veryficator.NumberVeryficator nv = new OperatorResolver.veryficator.NumberVeryficator();
        assertEquals(Operator.OTHERPOLAND, nv.verify("888888888"));
    }

    public void testTmobileVerify(){
        OperatorResolver.veryficator.NumberVeryficator nv = new OperatorResolver.veryficator.NumberVeryficator();
        assertEquals(Operator.TMOBILE, nv.verify("608800000"));
    }




}
