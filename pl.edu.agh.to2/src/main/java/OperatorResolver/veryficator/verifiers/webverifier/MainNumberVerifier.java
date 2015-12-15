package OperatorResolver.veryficator.verifiers.webverifier;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.veryficator.Verifier;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainNumberVerifier implements Verifier {

	private String number;
	private PageDownloader downloader;

	public MainNumberVerifier(PageDownloader downloader){
		this.downloader = downloader;
	}

	@Override
	public Operator verify(String num) {
		number = num;
		String line = null;

		try {
			if((line = downloader.getLine("http://download.t-mobile.pl/updir/updir.cgi?msisdn=" + num)) != null){
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

	private Operator getOperator(String operator) {

		Map<String, Operator> stringToOperator = new HashMap<>();
		stringToOperator.put("Orange", Operator.ORANGE);
		stringToOperator.put("PTC / T-Mobile", Operator.TMOBILE);
		stringToOperator.put("P4", Operator.PLAY);
		stringToOperator.put("Plus", Operator.PLUS);

		if (stringToOperator.containsKey(operator)) {
			return stringToOperator.get(operator);
		} else {
			return Operator.OTHERPOLAND;
		}
	}
}