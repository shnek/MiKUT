package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Verifiers implements Verifier{

    private List<Verifier> verifierList;
    private Map<String, Operator> numberToOperatorCache = new HashMap<>();

    public Verifiers() {
        this.verifierList = new ArrayList<>();
    }

    public Operator verify(String number) {


        if(numberToOperatorCache.containsKey(number)){
            return numberToOperatorCache.get(number);
        }

        for (Verifier verifier : verifierList) {
            Operator operator = verifier.verify(number);
            if (operator != null) {
                numberToOperatorCache.put(number, operator);
                return operator;
            }
        }

        return null;
    }

    private void cleanCache() {
        if(numberToOperatorCache.size() > 1000){
            numberToOperatorCache.clear();
        }
    }

    public void add(Verifier verifier) {
        verifierList.add(verifier);
    }

    public void remove(Verifier verifier) {
        verifierList.remove(verifier);
    }

}
