package BillingReader.offers.telepolis.setters;

import BillingReader.offers.Offer;
import org.jsoup.nodes.Element;

public class OuterCallSetter extends AttributeSetter {

    public OuterCallSetter() {
        super.setPattern("Koszt minuty do pozostałych sieci");
    }

    @Override
    public boolean matchesPattern(String label) {
        //System.out.println("does "+label+" contain "+pattern);
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setOuterCallCost(parseField(label));
    }
}
