package CostCalculator;

import BillingReader.Offer;
import BillingReader.Operator;
import OperatorResolver.Tests;
import OperatorResolver.operatorresolver.OperatorResolver;
import OperatorResolver.operatorresolver.OperatorResolverImp;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingdata.BillingLists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Mocker {

    public Billing getMockBilling() {
        Tests test = new Tests();
        BillingLists billingLists = test.getMockBillingLists();
        OperatorResolver op = new OperatorResolverImp(billingLists);
        return op.getBilling();
    }

    public List<Offer> getMockOffers() {
        List<Offer> offers = new ArrayList<>();

        Offer offer = new Offer();
        offer.setName("T-Mobile na karte");
        offer.setOperator(Operator.TMOBILE);
        offer.setAbonament(false);
        offer.setInnerCallCost(new BigDecimal("0.20"));
        offer.setOuterCallCost(new BigDecimal("0.30"));
        offer.setInnerSmsCost(new BigDecimal("0.05"));
        offer.setOuterSmsCost(new BigDecimal("0.20"));
        offer.setInnerMmsCost(new BigDecimal("0.30"));
        offer.setOuterMmsCost(new BigDecimal("0.60"));
        offer.setInternetMbCost(new BigDecimal("0.10"));
        offers.add(offer);



        return offers;
    }
}
