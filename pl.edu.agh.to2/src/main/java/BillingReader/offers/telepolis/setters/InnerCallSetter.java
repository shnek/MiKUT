package BillingReader.offers.telepolis.setters;

import BillingReader.offers.Offer;
import org.jsoup.nodes.Element;

public class InnerCallSetter extends AttributeSetter {

    public InnerCallSetter() {
        super.setPattern("Koszt minuty do własnej sieci");
    }

    @Override
    public boolean matchesPattern(String label) {
        //System.out.println("Here for inner call");
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setInnerCallCost(parseField(label));
    }
}
