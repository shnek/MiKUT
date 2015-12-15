package OperatorResolver.veryficator.verifiers.cacheverifier;

import OperatorResolver.operators.Operator;
import OperatorResolver.veryficator.Verifier;


public class CachedNumberVerifier implements Verifier {

    @Override
    public Operator verify(String num) {
        if(NumberCache.numberToOperator.containsKey(num)){
            return NumberCache.numberToOperator.get(num);
        } else {
            return null;
        }
    }

}
