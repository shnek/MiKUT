package CostCalculator;

import BillingReader.offers.Offer;
import CostCalculator.summarizer.OfferSummarizer;
import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostCalculator {

    private final Billing billing;
    private final List<Offer> offers;
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

    public BigDecimal getBestOfferValue(Map<Offer, BigDecimal> costs) {
        Map.Entry<Offer, BigDecimal> bestEntry = null;

        for (Map.Entry<Offer, BigDecimal> entry : costs.entrySet()) {
            if (bestEntry == null || entry.getValue().compareTo(bestEntry.getValue()) < 0) {
                bestEntry = entry;
            }
        }

        if (bestEntry == null) {
            throw new RuntimeException("cost map empty");
        }

        return bestEntry.getValue();
    }

    public Billing getBilling() {
        return billing;
    }
    public List<Offer> getOffers() {
        return offers;
    }
}
