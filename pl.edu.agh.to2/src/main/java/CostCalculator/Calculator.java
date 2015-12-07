package CostCalculator;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;
import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.operatorresolver.Services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {

    private Billing billing;
    private List<Offer> offers;

    public Calculator() {
    }

    public Calculator(Billing billing, List<Offer> offers) {
        this.billing = billing;
        this.offers = offers;
    }

    public Map<Offer, BigDecimal> calculateCosts() {
        Map<Offer, BigDecimal> costs = new HashMap<>();

        for (Offer offer : offers) {
            BigDecimal cost = new BigDecimal("0");

            int internetMb = (int) Math.ceil(billing.getInternet().getQuantity() / 1024);

            if (offer.isAbonament()) {

                cost = cost.add(offer.getMonthlyPayment());
                int internetDiff = internetMb - offer.getFreeInternetMb();
                if (internetDiff > 0) {
                    cost = cost.add(new BigDecimal(internetDiff).multiply(offer.getInternetMbCost()));
                }

            } else {

                BigDecimal internetPrice = new BigDecimal(internetMb).multiply(offer.getInternetMbCost());
                cost = cost.add(internetPrice);

                for (Map.Entry<Operator, Services> entry : billing.getOperatorToServices().entrySet()) {
                    int connections = entry.getValue().getConnections().getQuantity();
                    int sms = entry.getValue().getSms().getQuantity();
                    int mms = entry.getValue().getMms().getQuantity();

                    BigDecimal connectionsPrice;
                    BigDecimal smsPrice;
                    BigDecimal mmsPrice;

                    if (entry.getKey().equals(offer.getOperator())) {
                        connectionsPrice = new BigDecimal(connections).multiply(offer.getInnerCallCost());
                        smsPrice = new BigDecimal(sms).multiply(offer.getInnerSmsCost());
                        mmsPrice = new BigDecimal(mms).multiply(offer.getInnerMmsCost());
                    } else {
                        connectionsPrice = new BigDecimal(connections).multiply(offer.getOuterCallCost());
                        smsPrice = new BigDecimal(sms).multiply(offer.getOuterSmsCost());
                        mmsPrice = new BigDecimal(mms).multiply(offer.getOuterMmsCost());
                    }

                    cost = cost.add(connectionsPrice).add(smsPrice).add(mmsPrice);
                }
            }

            costs.put(offer, cost);
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
