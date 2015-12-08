package BillingReader.telepolis;

import java.util.List;

public class TelepolisAbonamentDownloader extends TelepolisDownloader {

    private String url;
    private boolean abonament;

    private NameSetter nameSetter;
    private OperatorSetter operatorSetter;
    private MonthlyPaymentSetter monthlyPaymentSetter;
    private List<AttributeSetter> fieldSetters;

    public TelepolisAbonamentDownloader() {
        super();
        this.url = "http://www.telepolis.pl/oferty-na-abonament";
        this.abonament = true;
    }

}
