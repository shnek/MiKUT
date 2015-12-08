package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class MonthlyPaymentSetter extends AttributeSetter {

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        offer.setMonthlyPayment(new BigDecimal(
                String.valueOf(label.select("span[itemprop=price]").first())
                        .replaceAll("<span itemprop=\"price\">", "")
                        .replaceAll("</span>", "")
                        .replaceAll(",",".")
        ));
    }
}
