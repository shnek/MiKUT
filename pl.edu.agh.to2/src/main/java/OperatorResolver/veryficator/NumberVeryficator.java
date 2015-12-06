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
// nazwa zwaizana z tmobile
// klasa odpowiedialana za komunikacje z siecia dostaje url i zwraca string
	private BufferedReader br;
	private String line;
	private InputStream is;

	@Override
	public Operator verify(String num) {

		is = null;
		br = UrlConnector.getBufferedReader("http://download.t-mobile.pl/updir/updir.cgi?msisdn=" + num, is);

		while ((line = readLines(br)) != null) {
			if (line.contains("Operator:")) {
				return findPattern();
			}
		}

		try {
			if (is != null)
				is.close();
		} catch (IOException ioe) {
			System.out.println("Closing exeption!");
		}

		return null;
	}

	private String readLines(BufferedReader br) {
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return line;
	}

	private Operator findPattern() {
		Pattern pattern = Pattern.compile("<td><b>Operator:</b></td>.*?</td>");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			String result = matcher.group(0).substring(29);
			result = result.replace("</td>", "");
			if (!result.equals("zagraniczny")) {
				return getOperator(result);
			}
		}

		return null;
	}

	private Operator getOperator(String oper) {

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