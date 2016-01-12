package OperatorResolver.numbersverificator.verifiers.webverifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class PageDownloaderImpl implements PageDownloader{

    private boolean reconnect = true;
    private int second = 1000;
    private int iteration = 15;

    public String getLine(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL in PageDownloader.getLine is invalid!");
        }
        String line = null;
        reconnect = true;
        while(reconnect) {
            try (InputStream is = url.openStream()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    if (line.contains("Operator:")) {
                        reconnect = false;
                        iteration = 15;
                        return line;
                    } else if(line.contains("Access denied.")){
                        Thread.sleep(iteration * second);
                        iteration++;
                        reconnect = true;
                    } else {
                        reconnect = false;
                    }
                }
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return line;
    }


}
