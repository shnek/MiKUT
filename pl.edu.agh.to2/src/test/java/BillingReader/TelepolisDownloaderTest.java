package BillingReader;

import BillingReader.offers.Offer;
import BillingReader.offers.telepolis.setters.*;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TelepolisDownloaderTest {

    private AttributeSetter setter;
    private String element1;
    private Element element2;
    private Offer offer;
    private Tag tag = Tag.valueOf("td");

    @Test
    public void testInnerCall() {
        setter = new InnerCallSetter();
        offer = new Offer();
        element1 = "<td>Koszt minuty do własnej sieci</td>";
        element2 = new Element(tag,"url");
        element2.appendText("0,09 zł");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("0.09"), offer.getInnerCallCost());
    }

    @Test
    public void testOuterCall() {
        setter = new OuterCallSetter();
        offer = new Offer();
        element1 = "<td>Koszt minuty do pozostałych sieci</td>";
        element2 = new Element(tag,"url");
        element2.appendText("8,50 zł");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("8.50"),offer.getOuterCallCost());
    }

    @Test
    public void testInnerSms() {
        setter = new InnerSmsSetter();
        offer = new Offer();
        element1 = "<td>Opłata za SMS do własnej sieci</td>";
        element2 = new Element(tag,"url");
        element2.appendText("8,50 zł");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("8.50"), offer.getInnerSmsCost());
    }

    @Test
    public void testOuterSms() {
        setter = new OuterSmsSetter();
        offer = new Offer();
        element1 = "<td>Opłata za SMS do pozostałych sieci</td>";
        element2 = new Element(tag,"url");
        element2.appendText("8,50 zł");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("8.50"), offer.getOuterSmsCost());
    }

    @Test
    public void testInnerMms() {
        setter = new InnerMmsSetter();
        offer = new Offer();
        element1 = "<td>Opłata za MMS do własnej sieci</td>";
        element2 = new Element(tag,"url");
        element2.appendText("8,50 zł");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("8.50"), offer.getInnerMmsCost());
    }

    @Test
    public void testOuterMms() {
        setter = new OuterMmsSetter();
        offer = new Offer();
        element1 = "<td>Opłata za MMS do pozostałych sieci</td>";
        element2 = new Element(tag,"url");
        element2.appendText("8,50 zł");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("8.50"), offer.getOuterMmsCost());
    }

    @Test
    public void testInternetMbCost() {
        setter = new InternetFieldsSetter();
        offer = new Offer();
        element1 = "<td colspan=\"3\" class=\"header\">Pakiet internetowy</td>";
        String message = "0,01 zł/20 kB;" +
                " możliwość wykupienia pakietu &quot;NetGT 500 MB&quot; za 6,99 zł/30 dni," +
                " &quot;NetGT 1 GB&quot; za 7,99 zł/30dni lub &quot;NetGT 4GB&quot; za 14,99 zł/30 dni";
        element2 = new Element(Tag.valueOf("td colspan"),"url");
        element2.appendText(message);
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(new BigDecimal("0.5"), offer.getInternetMbCost());
    }

    @Test
    public void testFreeInternetAmount() {
        setter = new InternetFieldsSetter();
        offer = new Offer();
        element1 = "<td colspan=\"3\" class=\"header\">Pakiet internetowy</td>";
        element2 = new Element(tag,"url");
        element2.appendText("12 GB");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertEquals(Double.valueOf(12000.0), Double.valueOf(offer.getFreeInternetMb()));
    }

}
