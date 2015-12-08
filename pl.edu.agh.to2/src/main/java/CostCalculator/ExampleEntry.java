package CostCalculator;


public class ExampleEntry {

    private int id;
    private String operatorName;
    private double totalCost;
    private double smsCost;
    private double minuteCost;
    private double internetCost;

    public ExampleEntry(int id, String operatorName, double totalCost, double smsCost, double minuteCost, double internetCost) {
        this.id = id;
        this.operatorName = operatorName;
        this.totalCost = totalCost;
        this.smsCost = smsCost;
        this.minuteCost = minuteCost;
        this.internetCost = internetCost;
    }

    public int getId() {
        return id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getSmsCost() {
        return smsCost;
    }

    public double getMinuteCost() {
        return minuteCost;
    }

    public double getInternetCost() {
        return internetCost;
    }
}
