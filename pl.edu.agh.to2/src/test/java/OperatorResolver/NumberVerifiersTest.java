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

@RunWith(Parameterized.class)
public class NumberVerifiersTest  {

    private String number;
    private Operator expectedOperator;
    private WebNumberVerifier webNumberVerifier;
    private PrefixNumberVerifier prefixNumberVerifier;


    @Before
    public void initialize() {
        webNumberVerifier = PowerMockito.mock(WebNumberVerifier.class);
        prefixNumberVerifier = PowerMockito.mock(PrefixNumberVerifier.class);
    }

    public NumberVerifiersTest(String number, Operator expectedOperator ) {
        this.number = number;
        this.expectedOperator = expectedOperator;
    }

    @Parameterized.Parameters
    public static Collection numberOperatorVersion() {
        return Arrays.asList(new Object[][] {
                { "48664097160", Operator.TMOBILE},
                { "43664097160", Operator.EURO}
        });
    }

    @Test
    public void testVerifiers() throws MalformedURLException  {

        Mockito.when(webNumberVerifier.verify("48664097160")).thenReturn(expectedOperator);
        Mockito.when(prefixNumberVerifier.verify("43664097160")).thenReturn(expectedOperator);

        Verifiers verifiers = new Verifiers();
        verifiers.add(webNumberVerifier);
        verifiers.add(prefixNumberVerifier);

        Operator operator = verifiers.verify(number);
        assertEquals(operator, expectedOperator);

    }


}
