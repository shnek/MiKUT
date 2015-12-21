package BillingReader.billings;

import OperatorResolver.operatorresolver.billingcontainers.Billing;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class BillingReader {

    public abstract Billing readBilling (File file) throws IncorrectExtensionException, FileNotFoundException;

    public boolean extensionCorrect (String expectedExtension, File file) {
        String fileName = file.getName();
        String extension;

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        } else return false;

        return expectedExtension == extension;
    }

}
