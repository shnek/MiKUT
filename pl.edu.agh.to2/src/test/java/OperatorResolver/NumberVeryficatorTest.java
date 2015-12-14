package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.veryficator.*;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
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
    public void TMobileNumberVeryficatorTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "888888888")).thenReturn("<td><b>Operator:</b></td><td>PTC / T-Mobile</td>\n");

        Veryficator mainVeryficator = new MainNumberVeryficator();
        Operator operator = mainVeryficator.verify("888888888");
        assertEquals(operator, Operator.TMOBILE);
    }

    @Test
    public void OrangeNumberVeryficatorTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "510100100")).thenReturn("<td><b>Operator:</b></td><td>Orange</td>\n");

        Veryficator mainVeryficator = new MainNumberVeryficator();
        Operator operator = mainVeryficator.verify("510100100");
        assertEquals(operator, Operator.ORANGE);
    }

    @Test
    public void PlayNumberVeryficatorTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "792600000")).thenReturn("<td><b>Operator:</b></td><td>P4</td>\n");

        Veryficator mainVeryficator = new MainNumberVeryficator();
        Operator operator = mainVeryficator.verify("792600000");
        assertEquals(operator, Operator.PLAY);
    }

    @Test
    public void PlusNumberVeryficatorTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "695416939")).thenReturn("<td><b>Operator:</b></td><td>Plus</td>\n");

        Veryficator mainVeryficator = new MainNumberVeryficator();
        Operator operator = mainVeryficator.verify("695416939");
        assertEquals(operator, Operator.PLUS);
    }

    @Test
    public void OtherPolandNumberVeryficatorTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "888888888")).thenReturn("<td><b>Operator:</b></td><td>Heyah</td>\n");

        Veryficator mainVeryficator = new MainNumberVeryficator();
        Operator operator = mainVeryficator.verify("888888888");
        assertEquals(operator, Operator.OTHERPOLAND);
    }


    @Test
    public void LandlineVeryficatorTest(){
        BasicConfigurator.configure();

        Prefixes prefixes = PowerMockito.mock(Prefixes.class);
        PowerMockito.when(prefixes.getAreaToPrefix("4817")).thenReturn(Operator.LANDLINE);

        Veryficator veryficator = new OtherNumberVeryficator(prefixes);
        Operator operator = veryficator.verify("48177881234");
        assertEquals(operator, Operator.LANDLINE);
    }
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
