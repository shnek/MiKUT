package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.veryficator.NumberVeryficator;
import OperatorResolver.veryficator.Veryficator;
import junit.framework.TestCase;

/**
 * Created by kuba on 09.11.15.
 */
public class NumberVeryficatorTest extends TestCase{

    public void testPlayVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.PLAY, nv.verify("792600000"));
    }
    public void testHeyahVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.OTHERPOLAND, nv.verify("888888888"));
    }

    public void testTmobileVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.TMOBILE, nv.verify("608800000"));
    }
    //510100100 Orange
//        664097160 PTC / T-Mobile
//        501936076 P4
//        695 416 939 Plus

    public void testOrangeVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.ORANGE, nv.verify("510100100"));
    }

    public void testPlusVer(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.PLUS, nv.verify("695416939"));
    }
}
