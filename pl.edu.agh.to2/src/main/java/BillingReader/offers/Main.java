package BillingReader.offers;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        OfferDownloader dl = new OfferDownloader();
        List<Offer> offers = dl.getOffers();

        for (Offer x : offers) {
            try {
                System.out.println(x);
            } catch (NullPointerException e) {
                System.out.println("--Exception in offer "+x.getName());
                e.printStackTrace();
            }
        }
    }
}
