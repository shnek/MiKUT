package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class Verifiers implements Verifier{

    private List<Verifier> verifierList;

    LoadingCache<String, Operator> numberToOperatorCache;

    public Verifiers() {
        this.verifierList = new ArrayList<>();
        numberToOperatorCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(
                        new CacheLoader<String, Operator>() {
                            public Operator load(String number){
                                return verifyNewNumber(number);
                            }
                        });
    }

    public Operator verify(String number) {

        try {
            return numberToOperatorCache.get(number);
        } catch (ExecutionException e) {
            throw new IllegalStateException("No number verifier found for "+number);
        }
    }

    private Operator verifyNewNumber(String number){
        for (Verifier verifier : verifierList) {
            Operator operator = verifier.verify(number);
            if (operator != null) {
                numberToOperatorCache.put(number, operator);
                return operator;
            }
        }

        throw new IllegalStateException("No number verifier found for "+number);
    }

    public void add(Verifier verifier) {
        verifierList.add(verifier);
    }

}
