package OperatorResolver;

import OperatorResolver.operatorresolver.Operator;
import OperatorResolver.operatorresolver.OperatorResolver;
import OperatorResolver.operatorresolver.OperatorResolverImp;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import OperatorResolver.operatorresolver.billingcontainers.Services;
import OperatorResolver.operatorresolver.billingdata.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tests {

    private Integer number = 664000000;
    private int turn = 0;

    public static void main(String args[]){

        Tests test = new Tests();
        BillingLists billingLists = test.getMockBillingLists();

        OperatorResolver op = new OperatorResolverImp(billingLists);

        Billing billing = op.getBilling();

        System.out.println("INTERNET: "+billing.getInternet().getQuantity());
        for(Map.Entry<Operator, Services> entry : billing.getOperatorToServices().entrySet()){

            System.out.println("OPERATOR: "+entry.getKey());
            System.out.println("\tPOLACZENIA: "+entry.getValue().getConnections().getQuantity());
            System.out.println("\tSMS: "+entry.getValue().getSms().getQuantity());
            System.out.println("\tMMS: "+entry.getValue().getMms().getQuantity());

        }
    }

    public String getNumber(){
        turn++;
        if(turn == 6){
            ++number;
            turn = 0;
        }
        return number.toString();
    }

    public BillingLists getMockBillingLists(){

        List<Dial> dList = new LinkedList<>();
        List<Sms> sList = new LinkedList<>();
        List<Mms> mList = new LinkedList<>();
        List<Transfer> tList = new LinkedList<>();

        Random generator = new Random();

        for(int i = 0; i < 10000; i++){

            switch (i%4) {
                case 0:
                    dList.add(new Dial(getNumber(), generator.nextInt(100)));
                    break;
                case 1:
                    sList.add(new Sms(getNumber()));
                    break;
                case 2:
                    mList.add(new Mms(getNumber()));
                    break;
                case 3:
                    tList.add(new Transfer(generator.nextInt(100)));
                    break;
            }

        }

        return  new BillingLists(dList, sList, mList, tList);
    }

}
