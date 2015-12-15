package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.veryficator.verifiers.webverifier.MainNumberVerifier;
import OperatorResolver.veryficator.Verifier;
import OperatorResolver.veryficator.verifiers.webverifier.PageDownloader;
import OperatorResolver.veryficator.verifiers.webverifier.PageDownloaderImpl;
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
public class NumberParametrizedVerifiersTest {

    private String line;
    private String number;
    private Operator expectedOperator;
    private PageDownloader downloader;
    private String url;

    @Before
    public void initialize() {
        downloader = PowerMockito.mock(PageDownloaderImpl.class);
        url = "http://download.t-mobile.pl/updir/updir.cgi?msisdn=";
    }

    public NumberParametrizedVerifiersTest(String number, Operator expectedOperator, String line) {
        this.number = number;
        this.expectedOperator = expectedOperator;
        this.line = line;
    }

    @Parameterized.Parameters
    public static Collection numberOperatorLine() {
        return Arrays.asList(new Object[][] {
            { "48664097160", Operator.TMOBILE, "<td><b>Operator:</b></td><td>PTC / T-Mobile</td>\n"},
            { "48510100100", Operator.ORANGE, "<td><b>Operator:</b></td><td>Orange</td>\n"},
            { "48792600000", Operator.PLAY, "<td><b>Operator:</b></td><td>P4</td>\n"},
            { "48695416939", Operator.PLUS, "<td><b>Operator:</b></td><td>Plus</td>\n"},
            { "48888888888", Operator.OTHERPOLAND, "<td><b>Operator:</b></td><td>Heyah</td>\n"}
        });
    }

    @Test
    public void testMainVerifier() throws MalformedURLException  {

        Mockito.when(downloader.getLine(url + number)).thenReturn(line);
        Verifier mainVerifier = new MainNumberVerifier(downloader);
        Operator operator = mainVerifier.verify(number);
        assertEquals(operator, expectedOperator);

    }

}
