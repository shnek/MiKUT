package CostCalculator;


public class RankingEntry {

    private int id;
    private String operatorName;
    private double totalCost;
    private double innerCallCost;
    private double outerCallCost;
    private double innerSmsCost;
    private double outerSmsCost;
    private double innerMmsCost;
    private double outerMmsCost;
    private double smsCost;
    private double minuteCost;
    private double internetCost;

    public RankingEntry(int id, String operatorName, double totalCost, double innerCallCost, double outerCallCost,
                        double innerSmsCost, double outerSmsCost, double innerMmsCost, double outerMmsCost, double internetCost) {
        this.id = id;
        this.operatorName = operatorName;
        this.totalCost = totalCost;
        this.innerCallCost = innerCallCost;
        this.outerCallCost = outerCallCost;
        this.innerSmsCost = innerSmsCost;
        this.outerSmsCost = outerSmsCost;
        this.innerMmsCost = innerMmsCost;
        this.outerMmsCost = outerMmsCost;
        this.internetCost = internetCost;
    }
}
