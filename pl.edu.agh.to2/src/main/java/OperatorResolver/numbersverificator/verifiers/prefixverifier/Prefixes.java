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
		fillMapWithPrefixes("EURO", Operator.EURO);
		fillMapWithPrefixes("FIRST", Operator.AREA1);
		fillMapWithPrefixes("SECOND", Operator.AREA2);
		fillMapWithPrefixes("LANDLINE", Operator.LANDLINE);

	}

	private void fillMapWithPrefixes(String PropertyName, Operator operator){
		for(String prefix : prefixes.getProperty(PropertyName).split(",")){
			prefixToArea.put(prefix, operator);
		}
	}

	public Operator getAreaToPrefix(String pref) {
		return prefixToArea.get(pref);
	}

}
