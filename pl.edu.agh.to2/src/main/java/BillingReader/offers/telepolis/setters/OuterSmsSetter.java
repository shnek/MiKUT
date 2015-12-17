package BillingReader.offers.telepolis.setters;

import BillingReader.offers.Offer;
import org.jsoup.nodes.Element;

public class OuterSmsSetter extends AttributeSetter {

    public OuterSmsSetter() {
        super.setPattern("Opłata za SMS do pozostałych sieci");
    }

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setOuterSmsCost(parseField(label));
    }
}
