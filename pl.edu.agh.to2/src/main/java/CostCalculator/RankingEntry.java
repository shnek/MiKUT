package CostCalculator;


public class RankingEntry {

    private int id;
    private String operatorName;
    private double totalCost;

    public RankingEntry(int id, String operatorName, double totalCost) {
        this.id = id;
        this.operatorName = operatorName;
        this.totalCost = totalCost;
    }
}
