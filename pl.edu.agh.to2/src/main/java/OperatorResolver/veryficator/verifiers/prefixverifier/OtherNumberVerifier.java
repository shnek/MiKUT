package OperatorResolver.veryficator.verifiers.prefixverifier;

import OperatorResolver.operators.Operator;
import OperatorResolver.veryficator.verifiers.cacheverifier.NumberCache;
import OperatorResolver.veryficator.Verifier;

public class OtherNumberVerifier implements Verifier {

	private Prefixes prefixes;

	public OtherNumberVerifier(Prefixes prefixes) {
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
