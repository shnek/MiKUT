package CostCalculator;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Billing billing = new Billing();
        List<Offer> offers = new ArrayList<>();
        CostCalculator costCalculator = new CostCalculator(billing, offers);

        Map<Offer, BigDecimal> calculatedOffers = costCalculator.calculateCosts();
        GUI ranking = new GUI();
        ranking.run(calculatedOffers);
    }
}
