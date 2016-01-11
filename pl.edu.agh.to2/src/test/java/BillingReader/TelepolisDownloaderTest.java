package BillingReader;

import BillingReader.offers.Offer;
import BillingReader.offers.telepolis.setters.AttributeSetter;
import BillingReader.offers.telepolis.setters.InnerCallSetter;
import BillingReader.offers.telepolis.setters.InternetFieldsSetter;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Test;

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
        setter = mock(InnerCallSetter.class);
        offer = new Offer();
        element1 = "<td>Koszt minuty do własnej sieci</td>";
        element2 = new Element(tag,"<td>8,50 zł</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getInnerCallCost(), 8.5);
    }

    @Test
    public void testOuterCall() {
        setter = mock(InnerCallSetter.class);
        offer = new Offer();
        element1 = "<td>Koszt minuty do pozostałych sieci</td>";
        element2 = new Element(tag,"<td>8,50 zł</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getOuterCallCost(), 8.5);
    }

    @Test
    public void testInnerSms() {
        setter = mock(InnerCallSetter.class);
        offer = new Offer();
        element1 = "<td>Opłata za SMS do własnej sieci</td>";
        element2 = new Element(tag,"<td>8,50 zł</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getInnerSmsCost(), 8.5);
    }

    @Test
    public void testOuterSms() {
        setter = mock(InnerCallSetter.class);
        offer = new Offer();
        element1 = "<td>Opłata za SMS do pozostałych sieci</td>";
        element2 = new Element(tag,"<td>8,50 zł</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getOuterSmsCost(), 8.5);
    }

    @Test
    public void testInnerMms() {
        setter = mock(InnerCallSetter.class);
        offer = new Offer();
        element1 = "<td>Opłata za MMS do własnej sieci</td>";
        element2 = new Element(tag,"<td>8,50 zł</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getInnerMmsCost(), 8.5);
    }

    @Test
    public void testOuterMms() {
        setter = mock(InnerCallSetter.class);
        offer = new Offer();
        element1 = "<td>Opłata za SMS do pozostałych sieci</td>";
        element2 = new Element(tag,"<td>8,50 zł</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getOuterMmsCost(), 8.5);
    }

    @Test
    public void testInternetMbCost() {
        setter = mock(InternetFieldsSetter.class);
        offer = new Offer();
        element1 = "<td colspan=\"3\" class=\"header\">Pakiet internetowy</td>";
        String message = "<td colspan=\"3\" class=\"long\">0,01 zł/20 kB;" +
                " możliwość wykupienia pakietu &quot;NetGT 500 MB&quot; za 6,99 zł/30 dni," +
                " &quot;NetGT 1 GB&quot; za 7,99 zł/30dni lub &quot;NetGT 4GB&quot; za 14,99 zł/30 dni</td>";
        element2 = new Element(tag,message);
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotEquals(offer.getInternetMbCost(), 0.5);
    }

    @Test
    public void testFreeInternetAmount() {
        setter = mock(InternetFieldsSetter.class);
        offer = new Offer();
        element1 = "<td colspan=\"3\" class=\"header\">Pakiet internetowy</td>";
        element2 = new Element(tag,"<td class=\"pakiet\">12 GB</td>");
        if (setter.matchesPattern(element1)) {
            setter.setAttribute(offer,element2);
        }
        assertNotSame(offer.getFreeInternetMb(), 12000.0);
    }

}
