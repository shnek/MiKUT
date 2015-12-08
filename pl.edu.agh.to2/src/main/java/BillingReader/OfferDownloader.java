package BillingReader;

import BillingReader.telepolis.TelepolisAbonamentDownloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferDownloader {

    private List<Offer> offers;

    public OfferDownloader () {
        this.offers = new ArrayList<Offer>();
    }

    private void downloadAll() throws IOException {
        PageDownloader td = new TelepolisAbonamentDownloader();
        offers.addAll(td.download());
    }

    public List<Offer> getOffers() throws IOException {
        this.downloadAll();
        return offers;
    }
}
