package BillingReader.billings;

import BillingReader.offers.telepolis.setters.InternetFieldsSetter;
import OperatorResolver.operatorresolver.OperatorResolverImp;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingdata.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.mockito.internal.util.collections.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlusReader extends BillingReader {

    private String plusExtension = "pdf";
    private List<Dial> dialList = new ArrayList<>();
    private List<Sms> smsList = new ArrayList<>();
    private List<Mms> mmsList = new ArrayList<>();
    private List<Transfer> transferList = new ArrayList<>();

    @Override
    public Billing readBilling(File file) throws IOException {
        readFile(file);
        BillingLists lists = new BillingLists(dialList, smsList, mmsList, transferList);
        OperatorResolverImp resolver = new OperatorResolverImp(lists);
        return resolver.getBilling();
    }

    private void readFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        PdfReader reader = new PdfReader(inputStream);
        int numberOfPages = reader.getNumberOfPages();

        for (int page = 1; page <= numberOfPages; page++) {
            String pageContent = PdfTextExtractor.getTextFromPage(reader, page);
            List<String> lines = new ArrayList<>(Arrays.asList(pageContent.split("\n")));

            for (String line : lines) {
                if (line.matches("^\\d{2}\\.\\d{2}\\.\\d{4}.+")) {
                    parseLine(line);
                }
            }
        }
    }

    private void parseLine(String line) {
        String cutLine = line.substring(18);
        //System.out.print(line);
        try {
            if (cutLine.matches("\\d{5}.+")) {
                if (cutLine.toLowerCase().contains("kb")) {
                    mmsList.add(new Mms(getNumber(cutLine)));
                    //System.out.print(" mms\n");
                } else if (cutLine.contains(":")) {
                    Dial dial = getDialInfo(cutLine);
                    dialList.add(dial);
                    //System.out.print(" dial " + dial.getLength() + "\n");
                } else {
                    Sms sms = new Sms(getNumber(cutLine));
                    for (int q=0; q<getSmsCount(cutLine); q++) {smsList.add(sms);}
                    //System.out.print(" sms\n");
                }
            } else {
                if (cutLine.toLowerCase().contains("kb")) {
                    Transfer transfer = new Transfer(getKBytes(cutLine));
                    transferList.add(transfer);
                    //System.out.print(" internets " + transfer.getDataSize() + "\n");
                } else {
                    throw new IncorrectEntryException();
                }
            }
        } catch (IncorrectEntryException e) {
            e.printStackTrace();
        }
    }

    private Dial getDialInfo(String line) {
        String[] data = line.split("[^?0-9]+");
        String number = data[0];
        String minutes = data[1];
        String seconds = data[2].substring(0,2);

        int length = 60*Integer.parseInt(minutes)+Integer.parseInt(seconds);
        return new Dial(number,length);
    }

    private int getKBytes (String line) {
        String str1 = line.split("KB")[0];
        String str = str1.replaceAll("[^-?0-9]+","");
        return Integer.parseInt(str);
    }

    private String getNumber (String line) {
        String[] data = line.split("[^?0-9]+");
        return data[0];
    }

    private int getSmsCount (String line) {
        String[] data = line.split("[^?0-9]+");
        return Integer.parseInt(data[0].substring(0,1));
    }
}
