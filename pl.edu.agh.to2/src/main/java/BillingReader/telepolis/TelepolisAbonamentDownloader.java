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
        super.setUrl("http://www.telepolis.pl/oferty-na-abonament");
        super.setAbonament(true);
    }

}
