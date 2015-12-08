package CostCalculator;

import BillingReader.Offer;
import OperatorResolver.operatorresolver.Billing;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Billing billing = new Billing();
        List<Offer> offers = new ArrayList<>();
        CostCalculator costCalculator = new CostCalculator(billing, offers);
        costCalculator.calculateCosts();
    }
}
