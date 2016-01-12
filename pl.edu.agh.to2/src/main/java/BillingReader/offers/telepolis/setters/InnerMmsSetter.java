package BillingReader.offers.telepolis.setters;

import BillingReader.offers.Offer;
import org.jsoup.nodes.Element;

public class InnerMmsSetter extends AttributeSetter {

    public InnerMmsSetter() {
        super.setPattern("Opłata za MMS do własnej sieci");
    }

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setInnerMmsCost(parseField(label));
    }
}
