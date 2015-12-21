package BillingReader.offers;

import BillingReader.offers.telepolis.TelepolisCardDownloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfferDownloader {

    private List<Offer> offers;

    public OfferDownloader () {
        this.offers = new ArrayList<Offer>();
    }

    private void downloadAll() throws IOException {
        PageDownloader td = new TelepolisCardDownloader();
        offers.addAll(td.download());
    }

    public List<Offer> getOffers() throws IOException {
        this.downloadAll();
        return offers;
    }
}
