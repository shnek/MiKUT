package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wojci on 06.12.2015.
 */
public class Verifiers implements Verifier{
    private List<Verifier> veryficatorList;

    public Verifiers() {
        this.veryficatorList = new ArrayList<>();
    }

    public Operator verify(String num) {

        NumberCache.cleanCache();

        for (Verifier veryficator : veryficatorList) {
            Operator operator = veryficator.verify(num);
            if (operator != null) {
                return operator;
            }
        }

        return null;
    }

    public void add(Verifier verifier) {
        veryficatorList.add(verifier);
    }

    public void remove(Verifier verifier) {
        veryficatorList.remove(verifier);
    }

}
