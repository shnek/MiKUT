package OperatorResolver.numbersverificator.verifiers.webverifier;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.numbersverificator.Verifier;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebNumberVerifier implements Verifier {

	private String number;
	private PageDownloader downloader;
	private Map<String, Operator> stringToOperator;
	public WebNumberVerifier(PageDownloader downloader){
		this.downloader = downloader;
		stringToOperator = new HashMap<>();
		stringToOperator.put("Orange", Operator.ORANGE);
		stringToOperator.put("PTC / T-Mobile", Operator.TMOBILE);
		stringToOperator.put("P4", Operator.PLAY);
		stringToOperator.put("Plus", Operator.PLUS);
	}

	@Override
	public Operator verify(String number) {

		this.number = number;
		String line = null;
		Random generator = new Random();

		try {
			Thread.sleep(5000);
			long start = System.currentTimeMillis();
			if((line = downloader.getLine("http://download.t-mobile.pl/updir/updir.cgi?msisdn=" + number)) != null){
				System.out.println("WEB RESPONSE FOR: "+number+" EXE_TIME[ms]: "+(System.currentTimeMillis()-start));
				return findPattern(line);
			}
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		throw new RuntimeException("WebNumberVerifier.verify somehow didn't return any operator.");
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
		throw new RuntimeException("Pattern not found in WebNumberVerifier.findPattern().");
	}

	private Operator getOperator(String operator) {
		if (stringToOperator.containsKey(operator)) {
			return stringToOperator.get(operator);
		} else {
			return Operator.OTHERPOLAND;
		}
	}
}