package OperatorResolver.veryficator.verifiers.cacheverifier;

import OperatorResolver.operatorresolver.Operator;

import java.util.HashMap;
import java.util.Map;


public class NumberCache {
    //WeakHashMap
    public static Map<String, Operator> numberToOperator = new HashMap<>();

    public static void cleanCache() {
        if(NumberCache.numberToOperator.size() > 1000){
            NumberCache.numberToOperator.clear();
        }
    }
}
