package OperatorResolver.veryficator.verifiers.webverifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class PageDownloaderImpl implements PageDownloader{

    public String getLine(String stringUrl) throws MalformedURLException {
        URL url = new URL(stringUrl);
        String line;
        try (InputStream is = url.openStream()){
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                if (line.contains("Operator:")) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
