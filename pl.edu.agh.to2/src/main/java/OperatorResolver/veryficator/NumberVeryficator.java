package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by kuba on 2015-11-03.
 */
public class NumberVeryficator implements Veryficator {

	@Override
	public Operator verify(String num) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		try {
			url = new URL("http://download.t-mobile.pl/updir/updir.cgi?msisdn=" + num);
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				if (line.contains("Operator:")) {
					Pattern pattern = Pattern.compile("<td><b>Operator:</b></td>.*?</td>");
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						// parser danych
						String result = matcher.group(0).substring(29);
						result = result.replace("</td>", "");
						System.out.println(result);
						if (!result.equals("zagraniczny")) {
							return getOperator(result);
						}
					}
				}
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				System.out.println("Closing exeption!");
			}
		}
		return null;
	}

	public Operator getOperator(String oper) {

		Map<String, Operator> stringToOperator = new HashMap<>();
		stringToOperator.put("Orange", Operator.ORANGE);
		stringToOperator.put("PTC / T-Mobile", Operator.TMOBILE);
		stringToOperator.put("P4", Operator.PLAY);
		stringToOperator.put("Plus", Operator.PLUS);

		if (stringToOperator.containsKey(oper)) {
			return stringToOperator.get(oper);
		} else {
			return Operator.OTHERPOLAND;
		}
	}
}