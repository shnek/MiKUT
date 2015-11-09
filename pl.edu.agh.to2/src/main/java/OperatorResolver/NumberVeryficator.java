package OperatorResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kuba on 2015-11-03.
 */
public class NumberVeryficator {

    public String verify(String num){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        try {

            url = new URL("http://download.t-mobile.pl/updir/updir.cgi?msisdn=" + num);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null ) {
                if(line.contains("Operator:")){
                    Pattern pattern = Pattern.compile("<td><b>Operator:</b></td>.*?</td>");
                    Matcher matcher = pattern.matcher(line);
                    if(matcher.find()){
                        //parser danych, whatever
                        String result = matcher.group(0).substring(29);
                        result = result.replace("</td>", ".");
                        return result;
                    }
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return null;
    }

}