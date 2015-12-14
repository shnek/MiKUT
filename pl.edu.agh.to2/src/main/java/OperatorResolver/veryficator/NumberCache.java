package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wojci on 14.12.2015.
 */
public class NumberCache {

    public static Map<String, Operator> numberToOperator = new HashMap<>();

    public static void cleanCache() {
        if(NumberCache.numberToOperator.size() > 1000){
            NumberCache.numberToOperator.clear();
        }
    }
}
