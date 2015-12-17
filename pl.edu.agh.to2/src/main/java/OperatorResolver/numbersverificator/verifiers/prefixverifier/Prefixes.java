package OperatorResolver.numbersverificator.verifiers.prefixverifier;
import OperatorResolver.operatorresolver.Operator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Prefixes {

	private Map<String, Operator> prefixToArea;
	private Properties prefixes;

	public Prefixes() {

		prefixToArea = new HashMap<>();
		prefixes = new Properties();

		try {
			prefixes.loadFromXML(new FileInputStream("properties/prefixes.properties.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		prefixToArea.put(prefixes.getProperty("POLAND"), Operator.OTHERPOLAND);

		for (String s : prefixes.getProperty("EURO").split(",")) {
			prefixToArea.put(s, Operator.EURO);
		}

		for (String s : prefixes.getProperty("FIRST").split(",")) {
			prefixToArea.put(s, Operator.AREA1);
		}

		for (String s : prefixes.getProperty("SECOND").split(",")) {
			prefixToArea.put(s, Operator.AREA2);
		}

		for (String s : prefixes.getProperty("LANDLINE").split(",")) {
			prefixToArea.put(s, Operator.LANDLINE);
		}

	}

	public Operator getAreaToPrefix(String pref) {
		return prefixToArea.get(pref);
	}

}
