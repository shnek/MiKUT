package BillingReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
