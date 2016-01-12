package BillingReader.telepolis;

import BillingReader.Offer;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public abstract class AttributeSetter {

    private String pattern;

    public AttributeSetter () {
        pattern = "pattern";
    }

    public boolean matchesPattern (String label) {
        System.out.println("hello, checking if "+label+" contains "+pattern);
        return label.contains(pattern);
    }

    public abstract void setAttribute (Offer offer, Element label);

    protected BigDecimal parseField (Element e)
    {
        return new BigDecimal(
                String.valueOf(e)
                        .replaceAll("bez limitu","0")
                        .replaceAll("<td>","")
                        .replaceAll("</td>","")
                        .replaceAll(" zł","")
                        .replaceAll(",",".")
        );
    }

    public String getPattern() {
        return pattern;
    }
}