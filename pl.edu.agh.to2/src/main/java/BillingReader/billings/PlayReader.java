package BillingReader.billings;

import OperatorResolver.operatorresolver.OperatorResolverImp;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingdata.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class PlayReader extends BillingReader {

    String playExtension = "csv";
    List<Dial> dialList;
    List<Sms> smsList;
    List<Mms> mmsList;
    List<Transfer> transferList;

    @Override
    public Billing readBilling(File file) throws FileNotFoundException {

        if (!extensionCorrect(playExtension,file)) {
            //TO DO LATER
        }

        Scanner scanner = new Scanner(file);
        String[] elements;

        while (scanner.hasNext()) {
            elements = scanner.next().split(",");
            if (!elements[1].equals("Wychodzące")) continue;

            switch(elements[2].toLowerCase()) {
                case "rozmowy głosowe":
                    //addDial()
                    break;
                case "sms":
                    //addSms()
                    break;
                case "mms":
                    //addMms
                    break;
                case "dane":
                    //addTransfer
                    break;
                default:
                    //ignore
                    break;
            }
        }

        BillingLists lists = new BillingLists(dialList,smsList,mmsList,transferList);
        OperatorResolverImp resolver = new OperatorResolverImp(lists);
        return resolver.getBilling();
    }

    private void addDial(String number, int lengthInSeconds) {
        //todo
    }

    private void addSms() {
        //todo
    }

    private void addMms() {
        //todo
    }

    private void addTransfer() {
        //todo
    }

}
