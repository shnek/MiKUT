package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

public class NameSetter extends AttributeSetter {

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute (Offer offer, Element label) {
        offer.setName(String.valueOf(label.select("span[itemprop=name]").first())
                        .replaceAll("<span itemprop=\"name\">", "")
                        .replaceAll("</span>", "")
        );
    }
}
