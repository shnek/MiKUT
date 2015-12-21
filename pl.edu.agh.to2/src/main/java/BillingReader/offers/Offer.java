package BillingReader.offers;

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
    private double freeInternetMb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isAbonament() {
        return abonament;
    }

    public void setAbonament(boolean abonament) {
        this.abonament = abonament;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getInnerCallCost() {
        return innerCallCost;
    }

    public void setInnerCallCost(BigDecimal innerCallCost) {
        this.innerCallCost = innerCallCost;
    }

    public BigDecimal getOuterCallCost() {
        return outerCallCost;
    }

    public void setOuterCallCost(BigDecimal outerCallCost) {
        this.outerCallCost = outerCallCost;
    }

    public BigDecimal getInnerSmsCost() {
        return innerSmsCost;
    }

    public void setInnerSmsCost(BigDecimal innerSmsCost) {
        this.innerSmsCost = innerSmsCost;
    }

    public BigDecimal getOuterSmsCost() {
        return outerSmsCost;
    }

    public void setOuterSmsCost(BigDecimal outerSmsCost) {
        this.outerSmsCost = outerSmsCost;
    }

    public BigDecimal getInnerMmsCost() {
        return innerMmsCost;
    }

    public void setInnerMmsCost(BigDecimal innerMmsCost) {
        this.innerMmsCost = innerMmsCost;
    }

    public BigDecimal getOuterMmsCost() {
        return outerMmsCost;
    }

    public void setOuterMmsCost(BigDecimal outerMmsCost) {
        this.outerMmsCost = outerMmsCost;
    }

    public BigDecimal getInternetMbCost() {
        return internetMbCost;
    }

    public void setInternetMbCost(BigDecimal internetMbCost) {
        this.internetMbCost = internetMbCost;
    }

    public double getFreeInternetMb() {
        return freeInternetMb;
    }

    public void setFreeInternetMb(double freeInternetMb) {
        this.freeInternetMb = freeInternetMb;
    }

    @Override
    public String toString()
    {
        return
                "OFFER: "+this.name+
                        "\n\tOperator: "+this.operator.getName()+
                        "\n\tAbonament: "+this.abonament+
                        "\n\tMonthly payment: "+this.monthlyPayment+
                        "\n\tFree internet: "+this.freeInternetMb+
                        "\n\tMB cost: "+this.internetMbCost+
                        "\n\tInner call cost: "+this.innerCallCost+
                        "\n\tOuter call cost: "+this.outerCallCost+
                        "\n\tInner sms cost: "+this.innerSmsCost+
                        "\n\tOuter sms cost: "+this.outerSmsCost+
                        "\n\tInner mms cost: "+this.innerMmsCost+
                        "\n\tOuter mms cost: "+this.outerMmsCost;
    }

}
