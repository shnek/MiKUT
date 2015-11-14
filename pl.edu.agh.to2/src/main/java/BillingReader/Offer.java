package BillingReader;

/**
 * Created by admin1 on 09.11.15.
 */
public class Offer {

    private String name;
    private String minutes;
    private String sms;
    private double internet;
    private int months;
    private double abonament;

    public Offer()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public double getInternet() {
        return internet;
    }

    public void setInternet(double internet) {
        this.internet = internet;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public double getAbonament() {
        return abonament;
    }

    public void setAbonament(double abonament) {
        this.abonament = abonament;
    }

    @Override
    public String toString()
    {
        return
                "OFFER: "+this.name+
                        "\n\tMinuty: "+this.minutes+
                        "\n\tSMS/MMS: "+this.sms+
                        "\n\tInternet: "+this.internet+
                        "\n\tMiesiace: "+this.months+
                        "\n\tAbonament: "+this.abonament;
    }
}
