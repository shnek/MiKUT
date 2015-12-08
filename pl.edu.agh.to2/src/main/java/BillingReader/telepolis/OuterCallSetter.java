package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

public class OuterCallSetter extends AttributeSetter {

    public OuterCallSetter() {
        super.setPattern("Koszt minuty do pozostałych sieci");
    }

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setOuterCallCost(parseField(label));
    }
}
