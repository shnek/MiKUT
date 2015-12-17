package BillingReader.offers.telepolis.setters;

import BillingReader.offers.Offer;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public abstract class AttributeSetter {


    public String pattern;

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean matchesPattern (String label) {
        //System.out.println("hello, checking if "+label+" contains "+pattern);
        return label.contains(pattern);
    }

    public abstract void setAttribute (Offer offer, Element label);

    protected BigDecimal parseField (Element e) {
        return new BigDecimal(
                String.valueOf(e)
                        .replaceAll("bez limitu","0")
                        .replaceAll("<td>","")
                        .replaceAll("</td>","")
                        .replaceAll("zł","")
                        .replaceAll(",",".")
                        .replaceAll(" ","")
        );
    }

}
