package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

public class OtherNumberVeryficator implements Veryficator {

	Prefixes prefixes;

	public OtherNumberVeryficator() {
		prefixes = new Prefixes();
	}

	@Override
	public Operator verify(String num) {

		if (prefixes.getAreaToPrefix(num.substring(0, 4)) != null) {
			return prefixes.getAreaToPrefix(num.substring(0, 4));
		} else if (prefixes.getAreaToPrefix(num.substring(0, 3)) != null) {
			return prefixes.getAreaToPrefix(num.substring(0, 3));
		} else if (prefixes.getAreaToPrefix(num.substring(0, 2)) != null) {
			return prefixes.getAreaToPrefix(num.substring(0, 2));
		} else if (prefixes.getAreaToPrefix(num.substring(0, 1)) != null) {
			return prefixes.getAreaToPrefix(num.substring(0, 1));
		} else {
			return Operator.AREA3;
		}

	}
}
