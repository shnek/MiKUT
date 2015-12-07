package BillingReader;

import OperatorResolver.operatorresolver.Operator;

import java.math.BigDecimal;

public class Offer {

    private String name;
    private Operator operator;
    private boolean abonament;
    private BigDecimal monthlyPayment;
    private BigDecimal innerCallCost;
    private BigDecimal outerCallCost;
    private BigDecimal innerSmsCost;
    private BigDecimal outerSmsCost;
    private BigDecimal innerMmsCost;
    private BigDecimal outerMmsCost;
    private BigDecimal internetMbCost;
    private int freeInternetMb;

    public String getName() {
        return name;
    }

    public Operator getOperator() {
        return operator;
    }

    public boolean isAbonament() {
        return abonament;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public BigDecimal getInnerCallCost() {
        return innerCallCost;
    }

    public BigDecimal getOuterCallCost() {
        return outerCallCost;
    }

    public BigDecimal getInnerSmsCost() {
        return innerSmsCost;
    }

    public BigDecimal getOuterSmsCost() {
        return outerSmsCost;
    }

    public BigDecimal getInnerMmsCost() {
        return innerMmsCost;
    }

    public BigDecimal getOuterMmsCost() {
        return outerMmsCost;
    }

    public BigDecimal getInternetMbCost() {
        return internetMbCost;
    }

    public int getFreeInternetMb() {
        return freeInternetMb;
    }
}
