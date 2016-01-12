package CostCalculator.summarizer;

import BillingReader.offers.Offer;
import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.math.BigDecimal;

public class AbonamentSummary extends OfferSummary {

    public AbonamentSummary(Offer offer) {
        super(offer);
    }

    @Override
    public BigDecimal cost(Billing billing) {
        BigDecimal cost = new BigDecimal("0");

        cost = cost.add(offer.getMonthlyPayment());
        int usedInternetMb = DataTransfer.KB.toMb(billing.getInternet().getQuantity());
        double internetDiff = usedInternetMb - offer.getFreeInternetMb();
        if (internetDiff > 0) {
            cost = cost.add(new BigDecimal(internetDiff).multiply(offer.getInternetMbCost()));
        }

        return cost;
    }
}
