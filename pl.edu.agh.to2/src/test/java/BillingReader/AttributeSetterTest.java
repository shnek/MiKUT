package BillingReader;

import BillingReader.offers.Offer;
import BillingReader.offers.telepolis.setters.AttributeSetter;
import BillingReader.offers.telepolis.setters.InnerCallSetter;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

public class AttributeSetterTest {

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

}
