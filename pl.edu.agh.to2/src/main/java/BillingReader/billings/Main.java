package BillingReader.billings;
import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.io.*;

public class Main {

    public static void main (String[] args) throws IOException, IncorrectEntryException {

        BillingReader r = new PlusReader();
        File file = new File("rachunek.pdf");
        Billing b = r.readBilling(file);
    }
}
