package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.veryficator.MainNumberVeryficator;
import OperatorResolver.veryficator.PageDownloader;
import OperatorResolver.veryficator.Veryficator;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by kuba on 09.11.15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PageDownloader.class)
public class NumberVeryficatorTest  {



    @Test
    public void NumberVeryficatorTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "888888888")).thenReturn("<td><b>Operator:</b></td><td>PTC / T-Mobile</td>\n");

        Veryficator mainVeryficator = new MainNumberVeryficator();
        Operator operator = mainVeryficator.verify("888888888");
        assertEquals(operator, Operator.TMOBILE);
    }



//
//    @Test
//    public void testPlayVerify(){
//        Veryficator nv = new DTmobileNumberVeryficator();
//        assertEquals(Operator.PLAY, nv.verify("792600000"));
//    }
//
//    @Test
//    public void testHeyahVerify(){
//        Veryficator nv = new DTmobileNumberVeryficator();
//        assertEquals(Operator.OTHERPOLAND, nv.verify("888888888"));
//    }
//
//    @Test
//    public void testTmobileVerify(){
//        Veryficator nv = new DTmobileNumberVeryficator();
//        assertEquals(Operator.TMOBILE, nv.verify("608800000"));
//    }
//
//    @Test
//    public void testOrangeVerify(){
//        Veryficator nv = new DTmobileNumberVeryficator();
//        assertEquals(Operator.ORANGE, nv.verify("510100100"));
//    }
//
//    @Test
//    public void testPlusVer(){
//        Veryficator nv = new DTmobileNumberVeryficator();
//        assertEquals(Operator.PLUS, nv.verify("695416939"));
//    }
//
//    @Test
//    public void testLandlineVerifty(){
//        Veryficator veryficator = new OtherNumberVeryficator();
//        assertEquals(Operator.LANDLINE, veryficator.verify("48177881234"));
//    }
//
//    @Test
//    public void testEuro1Verify(){
//        System.out.println("Working Directory = " +
//                System.getProperty("user.dir"));
//        Veryficator veryficator = new OtherNumberVeryficator();
//        assertEquals(Operator.EURO, veryficator.verify("31888888888"));
//    }
//
//    @Test
//    public void testPlayVeryficators(){
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//        assertEquals(Operator.PLAY, nv.verify("792600000"));
//    }
//
//    @Test
//    public void testHeyahVeryficators(){
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//
//        assertEquals(Operator.OTHERPOLAND, nv.verify("888888888"));
//    }
//
//    @Test
//    public void testTmobileVeryficators(){
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//
//        assertEquals(Operator.TMOBILE, nv.verify("608800000"));
//    }
//
//    @Test
//    public void testOrangeVeryficators(){
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//
//        assertEquals(Operator.ORANGE, nv.verify("510100100"));
//    }
//
//    @Test
//    public void testPlusVeryficators(){
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//
//        assertEquals(Operator.PLUS, nv.verify("695416939"));
//    }
//
//    @Test
//    public void testLandlineVeryficators(){
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//
//        assertEquals(Operator.LANDLINE, nv.verify("48177881234"));
//    }
//
//    @Test
//    public void testEuro1Veryficators(){
//        System.out.println("Working Directory = " +
//                System.getProperty("user.dir"));
//        Veryficators nv = new Veryficators();
//
//        nv.add(new DTmobileNumberVeryficator());
//        nv.add(new OtherNumberVeryficator());
//
//
//        assertEquals(Operator.EURO, nv.verify("31888888888"));
//    }

}
