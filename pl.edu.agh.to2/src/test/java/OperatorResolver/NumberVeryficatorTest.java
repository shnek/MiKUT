package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.veryficator.NumberVeryficator;
import OperatorResolver.veryficator.OtherNumberVeryficator;
import OperatorResolver.veryficator.Veryficator;
import OperatorResolver.veryficator.Veryficators;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by kuba on 09.11.15.
 */
public class NumberVeryficatorTest extends TestCase  {

    @Test
    public void testPlayVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.PLAY, nv.verify("792600000"));
    }

    @Test
    public void testHeyahVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.OTHERPOLAND, nv.verify("888888888"));
    }

    @Test
    public void testTmobileVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.TMOBILE, nv.verify("608800000"));
    }

    @Test
    public void testOrangeVerify(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.ORANGE, nv.verify("510100100"));
    }

    @Test
    public void testPlusVer(){
        Veryficator nv = new NumberVeryficator();
        assertEquals(Operator.PLUS, nv.verify("695416939"));
    }

    @Test
    public void testLandlineVerifty(){
        Veryficator veryficator = new OtherNumberVeryficator();
        assertEquals(Operator.LANDLINE, veryficator.verify("48177881234"));
    }

    @Test
    public void testEuro1Verify(){
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        Veryficator veryficator = new OtherNumberVeryficator();
        assertEquals(Operator.EURO, veryficator.verify("31888888888"));
    }

    @Test
    public void testPlayVeryficators(){
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());

        assertEquals(Operator.PLAY, nv.verify("792600000"));
    }

    @Test
    public void testHeyahVeryficators(){
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());


        assertEquals(Operator.OTHERPOLAND, nv.verify("888888888"));
    }

    @Test
    public void testTmobileVeryficators(){
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());


        assertEquals(Operator.TMOBILE, nv.verify("608800000"));
    }

    @Test
    public void testOrangeVeryficators(){
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());


        assertEquals(Operator.ORANGE, nv.verify("510100100"));
    }

    @Test
    public void testPlusVeryficators(){
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());


        assertEquals(Operator.PLUS, nv.verify("695416939"));
    }

    @Test
    public void testLandlineVeryficators(){
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());


        assertEquals(Operator.LANDLINE, nv.verify("48177881234"));
    }

    @Test
    public void testEuro1Veryficators(){
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        Veryficators nv = new Veryficators();

        nv.add(new NumberVeryficator());
        nv.add(new OtherNumberVeryficator());


        assertEquals(Operator.EURO, nv.verify("31888888888"));
    }

}
