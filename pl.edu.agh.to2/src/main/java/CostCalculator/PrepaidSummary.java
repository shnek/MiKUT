package CostCalculator;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;
import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.operatorresolver.Services;

import java.math.BigDecimal;
import java.util.Map;

public class PrepaidSummary extends OfferSummary {

    protected PrepaidSummary(Offer offer) {
        super(offer);
    }

    @Override
    public BigDecimal cost(Billing billing) {
        BigDecimal cost = new BigDecimal("0");

        BigDecimal internetPrice = new BigDecimal(this.internetMb(billing)).multiply(offer.getInternetMbCost());
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

        return cost;
    }
}
