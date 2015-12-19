package OperatorResolver.numbersverificator.verifiers.prefixverifier;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.numbersverificator.Verifier;

public class PrefixNumberVerifier implements Verifier {

	private Prefixes prefixes;

	public PrefixNumberVerifier(Prefixes prefixes) {
		this.prefixes = prefixes;
	}

	@Override
	public Operator verify(String number) {

		for(int i = 4; i > 0; i--){

			if (prefixes.getAreaToPrefix(number.substring(0, i)) != null) {
				Operator result = prefixes.getAreaToPrefix(number.substring(0, i));
				return result;
			}
		}

		return Operator.AREA3;
	}
}
