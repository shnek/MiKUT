package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

public class InnerSmsSetter extends AttributeSetter {

    private String pattern;

    public InnerSmsSetter() {
        this.pattern = "Opłata za SMS do własnej sieci";
    }

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setInnerSmsCost(parseField(label));
    }
}
