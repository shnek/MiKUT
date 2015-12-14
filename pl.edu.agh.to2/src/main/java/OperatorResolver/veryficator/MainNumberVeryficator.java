package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by kuba on 2015-11-03.
 */
public class MainNumberVeryficator implements Veryficator {
// nazwa zwaizana z tmobile - zrobione
// klasa odpowiedialana za komunikacje z siecia dostaje url i zwraca string
	private String number;

	@Override
	public Operator verify(String num) {
		number = num;
		String line = null;

		try {
			if((line = PageDownloader.getLine("http://download.t-mobile.pl/updir/updir.cgi?msisdn=" + num)) != null){
				return findPattern(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}


	private Operator findPattern(String line) {
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
			NumberCache.numberToOperator.put(number, stringToOperator.get(oper));
			return stringToOperator.get(oper);
		} else {
			NumberCache.numberToOperator.put(number, Operator.OTHERPOLAND);
			return Operator.OTHERPOLAND;
		}
	}
}