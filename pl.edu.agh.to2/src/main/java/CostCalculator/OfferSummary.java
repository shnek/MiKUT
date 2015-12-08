package CostCalculator;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;

import java.math.BigDecimal;

public abstract class OfferSummary {

    protected Offer offer;

    protected OfferSummary(Offer offer) {
        this.offer = offer;
    }

    public abstract BigDecimal cost(Billing billing);

    public int internetMb(Billing billing) {
        return (int) Math.ceil(billing.getInternet().getQuantity() / 1024);
    }
}
