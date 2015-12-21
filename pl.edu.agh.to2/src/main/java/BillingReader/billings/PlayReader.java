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
            //TODO
        }

        Scanner scanner = new Scanner(file);
        readFile(scanner);

        BillingLists lists = new BillingLists(dialList, smsList, mmsList, transferList);
        OperatorResolverImp resolver = new OperatorResolverImp(lists);
        return resolver.getBilling();
    }

    private void readFile(Scanner scanner) {

        String phoneNr;
        int length;
        int kB;

        while (scanner.hasNext()) {
            String[] elements = scanner.next().split(",");

            if (!elements[1].equals("Wychodzące")) continue;
            switch(elements[2].toLowerCase()) {
                case "rozmowy głosowe":
                    phoneNr = elements[5];
                    length = toSeconds(elements[7]);
                    Dial dial = new Dial(phoneNr, length);
                    dialList.add(dial);
                    break;
                case "sms":
                    phoneNr = elements[5];
                    Sms sms = new Sms(phoneNr);
                    smsList.add(sms);
                    break;
                case "mms":
                    phoneNr = elements[5];
                    Mms mms = new Mms(phoneNr);
                    mmsList.add(mms);
                    break;
                case "dane":
                    kB = tokBytes(elements[7]);
                    Transfer transfer = new Transfer(kB);
                    transferList.add(transfer);
                    break;
                default:
                    break;
            }
        }
    }

    private int toSeconds(String text) {
        String withColon = text.substring(0,5);
        String minutes = withColon.substring(0,2);
        String seconds = withColon.substring(3);
        int allSeconds = Integer.parseInt(minutes)*60 + Integer.parseInt(seconds);
        return allSeconds;
    }

    private int tokBytes(String text) {
        String kBytes = text.split(",")[0];
        int kB = Integer.parseInt(kBytes);
        return kB;
    }

}
