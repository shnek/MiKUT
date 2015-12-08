package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

public class OuterCallSetter extends AttributeSetter {

    private String pattern;

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    public OuterCallSetter() {
        this.pattern = "Koszt minuty do pozostałych sieci";
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setOuterCallCost(parseField(label));
    }
}
