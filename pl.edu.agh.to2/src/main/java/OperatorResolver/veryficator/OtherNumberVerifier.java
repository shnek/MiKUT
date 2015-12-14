package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

public class OtherNumberVerifier implements Verifier {

	private Prefixes prefixes;

	public OtherNumberVerifier(Prefixes prefixes) {
		this.prefixes = prefixes;
	}

	@Override
	public Operator verify(String num) {

		if (prefixes.getAreaToPrefix(num.substring(0, 4)) != null) {

			Operator result = prefixes.getAreaToPrefix(num.substring(0, 4));
			NumberCache.numberToOperator.put(num, result);

			return result;

		} else if (prefixes.getAreaToPrefix(num.substring(0, 3)) != null) {

			Operator result =  prefixes.getAreaToPrefix(num.substring(0, 3));
			NumberCache.numberToOperator.put(num, result);

			return result;

		} else if (prefixes.getAreaToPrefix(num.substring(0, 2)) != null) {

			Operator result =  prefixes.getAreaToPrefix(num.substring(0, 2));
			NumberCache.numberToOperator.put(num, result);

			return result;

		} else if (prefixes.getAreaToPrefix(num.substring(0, 1)) != null) {

			Operator result =  prefixes.getAreaToPrefix(num.substring(0, 1));
			NumberCache.numberToOperator.put(num, result);

			return result;

		} else {

			Operator result =  Operator.AREA3;
			NumberCache.numberToOperator.put(num, result);

			return result;
		}

	}
}
