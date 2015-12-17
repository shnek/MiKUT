package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.numbersverificator.Verifiers;
import OperatorResolver.numbersverificator.verifiers.webverifier.WebNumberVerifier;
import OperatorResolver.numbersverificator.verifiers.prefixverifier.PrefixNumberVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by kuba on 09.11.15.
 */

@RunWith(Parameterized.class)
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
    private String number;
    private Operator expectedOperator;
    private WebNumberVerifier webNumberVerifier;
    private PrefixNumberVerifier prefixNumberVerifier;
    private int version;

    @Before
    public void initialize() {
        webNumberVerifier = PowerMockito.mock(WebNumberVerifier.class);
        prefixNumberVerifier = PowerMockito.mock(PrefixNumberVerifier.class);
    }

    public NumberVerifiersTest(String number, Operator expectedOperator, int version) {
        this.number = number;
        this.expectedOperator = expectedOperator;
        this.version = version;
    }

    @Parameterized.Parameters
    public static Collection numberOperatorVersion() {
        return Arrays.asList(new Object[][] {
                { "48664097160", Operator.TMOBILE, 0 },
                { "48510100100", Operator.ORANGE, 0 },
                { "48792600000", Operator.PLAY, 0 },
                { "48695416939", Operator.PLUS, 0 },
                { "48888888888", Operator.OTHERPOLAND, 0 },
                { "43664097160", Operator.EURO, 1 },
                { "355510100100", Operator.AREA1, 1 },
                { "679792600000", Operator.AREA2 , 1},
                { "679792600000", Operator.AREA3, 1 },
                { "481395416939", Operator.LANDLINE, 1 },
                { "48888888888", Operator.OTHERPOLAND, 1 }
        });
    }

    @Test
    public void testVerifiers() throws MalformedURLException  {

        if(version == 0){
            Mockito.when(webNumberVerifier.verify(number)).thenReturn(expectedOperator);
        } else {
            Mockito.when(prefixNumberVerifier.verify(number)).thenReturn(expectedOperator);
        }

        Verifiers verifiers = new Verifiers();
        verifiers.add(webNumberVerifier);
        verifiers.add(prefixNumberVerifier);

        Operator operator = verifiers.verify(number);
        assertEquals(operator, expectedOperator);

    }


}
