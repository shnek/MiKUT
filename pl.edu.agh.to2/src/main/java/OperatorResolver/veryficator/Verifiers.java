package OperatorResolver.veryficator;

import OperatorResolver.operators.Operator;
import OperatorResolver.veryficator.verifiers.cacheverifier.NumberCache;

import java.util.ArrayList;
import java.util.List;


public class Verifiers implements Verifier{

    private List<Verifier> verifierList;

    public Verifiers() {
        this.verifierList = new ArrayList<>();
    }

    public Operator verify(String number) {

        NumberCache.cleanCache();
//
//        if(NumberCache.numberToOperator.containsKey(number)){
//            return NumberCache.numberToOperator.get(number);
//        }
        for (Verifier verifier : verifierList) {
            Operator operator = verifier.verify(number);
            if (operator != null) {
                NumberCache.numberToOperator.put(number, operator);
                return operator;
            }
        }

        return null;
    }

    public void add(Verifier verifier) {
        verifierList.add(verifier);
    }

    public void remove(Verifier verifier) {
        verifierList.remove(verifier);
    }

}
