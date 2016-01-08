package CostCalculator.summarizer;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.math.BigDecimal;

public class OfferSummarizer {

    private final Billing billing;

    public OfferSummarizer(Billing billing) {
        this.billing = billing;
    }

    public BigDecimal summarize(Offer offer) {
        OfferSummary offerSummary;

        if (offer.isAbonament()) {
            offerSummary = new AbonamentSummary(offer);
        } else {
            offerSummary = new PrepaidSummary(offer);
        }

        return offerSummary.cost(billing);
    }
}
