package CostCalculator.summarizer;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;

import java.math.BigDecimal;

public class AbonamentSummary extends OfferSummary {

    public AbonamentSummary(Offer offer) {
        super(offer);
    }

    @Override
    public BigDecimal cost(Billing billing) {
        BigDecimal cost = new BigDecimal("0");

        cost = cost.add(offer.getMonthlyPayment());
        double internetDiff = this.internetMb(billing) - offer.getFreeInternetMb();
        if (internetDiff > 0) {
            cost = cost.add(new BigDecimal(internetDiff).multiply(offer.getInternetMbCost()));
        }

        return cost;
    }
}
