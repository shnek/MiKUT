package BillingReader.offers.telepolis.setters;

import BillingReader.offers.Offer;
import BillingReader.offers.Operator;
import org.jsoup.nodes.Element;

public class OperatorSetter extends AttributeSetter {

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setOperator(Operator.findByName(String.valueOf(label.select("span[itemprop=brand]").first())
                        .replaceAll("<span itemprop=\"brand\">", "")
                        .replaceAll("</span>", ""))
        );
    }
}
