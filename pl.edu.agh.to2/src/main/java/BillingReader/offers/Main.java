package BillingReader.offers;

import java.io.IOException;

public class Main {

    //static final int N = 19;

    public static void main(String[] args) throws IOException {

        OfferDownloader dl = new OfferDownloader();

        for (Offer x : dl.getOffers())
        {
            try {
                System.out.println(x);
            } catch (NullPointerException e) {
                //ignore
            }
        }
    }
}
