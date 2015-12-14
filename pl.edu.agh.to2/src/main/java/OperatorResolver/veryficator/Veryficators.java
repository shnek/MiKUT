package OperatorResolver.veryficator;

import OperatorResolver.operatorresolver.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wojci on 06.12.2015.
 */
public class Veryficators implements Veryficator{
    private List<Veryficator> veryficatorList;

    public Veryficators() {
        this.veryficatorList = new ArrayList<>();
    }

    public Operator verify(String num) {

        NumberCache.cleanCache();

        for (Veryficator veryficator : veryficatorList) {
            Operator operator = veryficator.verify(num);
            if (operator != null) {
                return operator;
            }
        }

        return null;
    }

    public void add(Veryficator veryficator) {
        veryficatorList.add(veryficator);
    }

    public void remove(Veryficator veryficator) {
        veryficatorList.remove(veryficator);
    }

}
