package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.numbersverificator.Verifier;
import OperatorResolver.numbersverificator.verifiers.prefixverifier.PrefixNumberVerifier;
import OperatorResolver.numbersverificator.verifiers.prefixverifier.Prefixes;
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
public class NumberParametrizedPrefixVerifierTest {

    private String prefix;
    private String number;
    private Operator expectedOperator;
    private Prefixes prefixes;


    @Before
    public void initialize() {
        prefixes = PowerMockito.mock(Prefixes.class);
    }

    public NumberParametrizedPrefixVerifierTest(String number, Operator expectedOperator, String prefix) {
        this.number = number;
        this.expectedOperator = expectedOperator;
        this.prefix = prefix;
    }

    @Parameterized.Parameters
    public static Collection numberOperatorPrefix() {
        return Arrays.asList(new Object[][] {
                { "43664097160", Operator.EURO, "43"},
                { "355510100100", Operator.AREA1, "35"},
                { "679792600000", Operator.AREA2, "679"},
                { "679792600000", Operator.AREA3, "6734"},
                { "481395416939", Operator.LANDLINE, "4813"},
                { "48888888888", Operator.OTHERPOLAND, "48"}
        });
    }

    @Test
    public void testMainVerifier() throws MalformedURLException  {

        Mockito.when(prefixes.getAreaToPrefix(prefix)).thenReturn(expectedOperator);
        Verifier otherVerifier = new PrefixNumberVerifier(prefixes);
        Operator operator = otherVerifier.verify(number);
        assertEquals(operator, expectedOperator);

    }

}
