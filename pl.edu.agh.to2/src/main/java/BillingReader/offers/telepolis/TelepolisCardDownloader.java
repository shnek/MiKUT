package BillingReader.offers.telepolis;

import BillingReader.offers.telepolis.setters.AttributeSetter;
import BillingReader.offers.telepolis.setters.MonthlyPaymentSetter;
import BillingReader.offers.telepolis.setters.NameSetter;
import BillingReader.offers.telepolis.setters.OperatorSetter;

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
        super.setUrl("http://www.telepolis.pl/na-karte");
        super.setAbonament(false);
    }

}
