package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

/**
 * Created by wojci on 14.12.2015.
 */
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
