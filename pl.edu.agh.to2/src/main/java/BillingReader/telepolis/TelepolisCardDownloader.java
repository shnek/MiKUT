package BillingReader.telepolis;

import java.util.List;

public class TelepolisCardDownloader extends TelepolisDownloader {

    private String url;
    private boolean abonament;

    private NameSetter nameSetter;
    private OperatorSetter operatorSetter;
    private MonthlyPaymentSetter monthlyPaymentSetter;
    private List<AttributeSetter> fieldSetters;

    public TelepolisCardDownloader() {
        super();
        this.url = "http://www.telepolis.pl/na-karte";
        this.abonament = false;
    }

}
