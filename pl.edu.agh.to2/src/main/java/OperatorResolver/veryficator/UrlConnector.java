package OperatorResolver.veryficator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wojci on 06.12.2015.
 */
public class UrlConnector {

    public static BufferedReader getBufferedReader(String stringUrl, InputStream is) {

        URL url = null;
        BufferedReader br;

        try {
            url = new URL(stringUrl);
            is = url.openStream(); // throws an IOException
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BufferedReader(new InputStreamReader(is));
    }

}
