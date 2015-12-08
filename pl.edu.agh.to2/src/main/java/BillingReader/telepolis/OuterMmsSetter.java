package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

public class OuterMmsSetter extends AttributeSetter {

    public OuterMmsSetter() {
        super.setPattern("Opłata za MMS do pozostałych sieci");
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setOuterMmsCost(parseField(label));
    }

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }
}
