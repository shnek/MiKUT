package BillingReader.billings;

import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.io.File;
import java.io.IOException;

public abstract class BillingReader {

    public abstract Billing readBilling (File file) throws IncorrectEntryException, IOException;

}
