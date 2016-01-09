package BillingReader.billings;

import OperatorResolver.operatorresolver.OperatorResolverImp;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingdata.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayReader extends BillingReader {

    private String playExtension = "csv";
    private List<Dial> dialList = new ArrayList<>();
    private List<Sms> smsList = new ArrayList<>();
    private List<Mms> mmsList = new ArrayList<>();
    private List<Transfer> transferList = new ArrayList<>();

    @Override
    public Billing readBilling(File file) throws FileNotFoundException {
        readFile(file);
        BillingLists lists = new BillingLists(dialList, smsList, mmsList, transferList);
        OperatorResolverImp resolver = new OperatorResolverImp(lists);
        return resolver.getBilling();
    }

    private void readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            String[] elements = scanner.next().split(",");
            parseLine(elements);
        }
        scanner.close();
    }

    public void parseLine(String[] elements) {
        String phoneNr;
        int length;
        int kB;
        try {
            if (!elements[1].contains("Wychodz")) return;
            switch (elements[2].toLowerCase()) {
                case "rozmowy g�osowe":
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
                    throw new IncorrectEntryException();
            }
        } catch (IncorrectEntryException e) {
            e.printStackTrace();
        }
    }

    public int toSeconds(String text) {
        String withColon = text.substring(0,5);
        String minutes = withColon.substring(0,2);
        String seconds = withColon.substring(3);
        int allSeconds = Integer.parseInt(minutes)*60 + Integer.parseInt(seconds);
        return allSeconds;
    }

    public int tokBytes(String text) {
        String kBytes = text.split(",")[0];
        int kB = Integer.parseInt(kBytes);
        return kB;
    }

    public List<Dial> getDialList() {
        return dialList;
    }

    public List<Sms> getSmsList() {
        return smsList;
    }

    public List<Mms> getMmsList() {
        return mmsList;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

}
