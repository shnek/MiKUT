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

    public String getLine(String stringUrl) throws MalformedURLException {
        URL url = new URL(stringUrl);
        String line;
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
                        System.out.println("Access denied. CZEKAM: [s]"+iteration);
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
        return null;
    }


}
