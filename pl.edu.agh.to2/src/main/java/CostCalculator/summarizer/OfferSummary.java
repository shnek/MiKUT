package CostCalculator.summarizer;

import BillingReader.offers.Offer;
import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.math.BigDecimal;

public abstract class OfferSummary {

    protected Offer offer;

    protected OfferSummary(Offer offer) {
        this.offer = offer;
    }

    public abstract BigDecimal cost(Billing billing);

}
