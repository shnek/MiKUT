package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

/**
 * Created by wojci on 14.12.2015.
 */
public class CachedNumberVeryficator implements Veryficator {

    @Override
    public Operator verify(String num) {
        if(NumberCache.numberToOperator.containsKey(num)){
            System.out.println("smignalem");
            return NumberCache.numberToOperator.get(num);
        } else {
            return null;
        }
    }

}
