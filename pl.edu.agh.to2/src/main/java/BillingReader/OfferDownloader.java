import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferDownloader {

    private List<Offer> offers;
    private String cardOffersURL;
    private String abonamentOffersUrl;

    public OfferDownloader () {
        this.offers = new ArrayList<Offer>();
        cardOffersURL = "http://www.telepolis.pl/na-karte";
        abonamentOffersUrl = "http://www.telepolis.pl/oferty-na-abonament";
    }

    private void downloadAll() throws IOException {
        TelepolisDownloader td = new TelepolisDownloader();
        this.offers.addAll(td.download(cardOffersURL,false));
        this.offers.addAll(td.download(abonamentOffersUrl,true));
    }

    public List<Offer> getOffers() throws IOException {
        this.downloadAll();
        return offers;
    }
}
