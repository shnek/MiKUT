package CostCalculator;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostCalculator {

    private Billing billing;
    private List<Offer> offers;
    private OfferSummarizer offerSummarizer;

    public CostCalculator(Billing billing, List<Offer> offers) {
        this.billing = billing;
        this.offers = offers;
        this.offerSummarizer = new OfferSummarizer(billing);
    }

    public Map<Offer, BigDecimal> calculateCosts() {
        Map<Offer, BigDecimal> costs = new HashMap<>();

        for (Offer offer : offers) {
            costs.put(offer, offerSummarizer.summarize(offer));
        }

        return costs;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}
