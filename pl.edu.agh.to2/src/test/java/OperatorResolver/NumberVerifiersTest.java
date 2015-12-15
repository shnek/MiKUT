package OperatorResolver;

import OperatorResolver.operators.Operator;
import OperatorResolver.veryficator.verifiers.webverifier.MainNumberVerifier;
import OperatorResolver.veryficator.verifiers.prefixverifier.OtherNumberVerifier;
import OperatorResolver.veryficator.Verifier;
import OperatorResolver.veryficator.verifiers.prefixverifier.Prefixes;
import OperatorResolver.veryficator.verifiers.webverifier.PageDownloader;
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
public class NumberVerifiersTest  {
    /**
     *
     * data test driven junit
     * parametriezed.class
     *
     *
     * guawa - cache - utilsy do kolekcji od google
     *
     */

    @Test
    public void TMobileNumberVerifierTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "888888888")).thenReturn("<td><b>Operator:</b></td><td>PTC / T-Mobile</td>\n");

        Verifier mainVerifier = new MainNumberVerifier();
        Operator operator = mainVerifier.verify("888888888");
        assertEquals(operator, Operator.TMOBILE);
    }

    @Test
    public void OrangeNumberVerifierTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "510100100")).thenReturn("<td><b>Operator:</b></td><td>Orange</td>\n");

        Verifier mainVerifier = new MainNumberVerifier();
        Operator operator = mainVerifier.verify("510100100");
        assertEquals(operator, Operator.ORANGE);
    }

    @Test
    public void PlayNumberVerifierTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "792600000")).thenReturn("<td><b>Operator:</b></td><td>P4</td>\n");

        Verifier mainVerifier = new MainNumberVerifier();
        Operator operator = mainVerifier.verify("792600000");
        assertEquals(operator, Operator.PLAY);
    }

    @Test
    public void PlusNumberVerifierTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "695416939")).thenReturn("<td><b>Operator:</b></td><td>Plus</td>\n");

        Verifier mainVerifier = new MainNumberVerifier();
        Operator operator = mainVerifier.verify("695416939");
        assertEquals(operator, Operator.PLUS);
    }

    @Test
    public void OtherPolandNumberVerifierTest() throws MalformedURLException {
        BasicConfigurator.configure();

        PowerMockito.mockStatic(PageDownloader.class);
        String line = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
        Mockito.when(PageDownloader.getLine(line + "888888888")).thenReturn("<td><b>Operator:</b></td><td>Heyah</td>\n");

        Verifier mainVerifier = new MainNumberVerifier();
        Operator operator = mainVerifier.verify("888888888");
        assertEquals(operator, Operator.OTHERPOLAND);
    }


    @Test
    public void LandlineVerifierTest(){
        BasicConfigurator.configure();

        Prefixes prefixes = PowerMockito.mock(Prefixes.class);
        PowerMockito.when(prefixes.getAreaToPrefix("4817")).thenReturn(Operator.LANDLINE);

        Verifier verifier = new OtherNumberVerifier(prefixes);
        Operator operator = verifier.verify("48177881234");
        assertEquals(operator, Operator.LANDLINE);
    }

    @Test
    public void EuroVerifierTest(){
        BasicConfigurator.configure();

        Prefixes prefixes = PowerMockito.mock(Prefixes.class);
        PowerMockito.when(prefixes.getAreaToPrefix("31")).thenReturn(Operator.EURO);

        Verifier verifier = new OtherNumberVerifier(prefixes);
        Operator operator = verifier.verify("31888888888");
        assertEquals(operator, Operator.EURO);
    }

    @Test
    public void FirstVerifierTest(){
        BasicConfigurator.configure();

        Prefixes prefixes = PowerMockito.mock(Prefixes.class);
        PowerMockito.when(prefixes.getAreaToPrefix("31")).thenReturn(Operator.EURO);

        Verifier veryficator = new OtherNumberVerifier(prefixes);
        Operator operator = veryficator.verify("31888888888");
        assertEquals(operator, Operator.EURO);
    }


}
