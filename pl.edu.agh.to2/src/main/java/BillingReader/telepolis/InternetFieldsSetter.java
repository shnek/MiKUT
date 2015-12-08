package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.telepolis.AttributeSetter;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

public class InternetFieldsSetter extends AttributeSetter {

    private String pattern;

    public InternetFieldsSetter() {
        this.pattern = "Pakiet internetowy";
    }

    @Override
    public boolean matchesPattern(String label) {
        return super.matchesPattern(label);
    }

    @Override
    public void setAttribute(Offer offer, Element label) {
        if (String.valueOf(label).contains("colspan")) {
            setInternetCost(offer,label);
        }
        else {
            setFreeInternet(offer,label);
        }
    }

    private void setFreeInternet (Offer offer, Element label) {
        String tmp1 = String.valueOf(label).split(">")[1];
        String tmp2 = tmp1.split(";")[0];
        double x1, x2;
        String[] netInfo = tmp2.split("/|\\(");
        x1 = Double.parseDouble(
                netInfo[0].replaceAll(",",".").replaceAll("zł","").replaceAll(" ","")
        );
        if (netInfo[1].toLowerCase().contains("kb")) {
            x2 = Double.parseDouble(
                    netInfo[1].toLowerCase().replaceAll("kb", "").replaceAll(" ","")
            );
            offer.setInternetMbCost(new BigDecimal(String.valueOf((1000/x2)*x1)));
        }
        else if (netInfo[1].toLowerCase().contains("mb")) {
            x2 = Double.parseDouble(
                    netInfo[1].toLowerCase().replaceAll("mb", "").replaceAll(" ","")
            );
            offer.setInternetMbCost(new BigDecimal(String.valueOf(x1/x2)));
        }
    }

    private void setInternetCost (Offer offer, Element label) {
        String netData = String.valueOf(label);
        netData = netData.split("<|>")[2];
        if (netData.toLowerCase().contains("gb")) {
            offer.setFreeInternetMb(1000 * Double.parseDouble(
                    netData.toLowerCase().replaceAll("gb", "").replaceAll(" ","")
            ));
        }
        else if (netData.toLowerCase().contains("mb")) {
            offer.setFreeInternetMb(Double.parseDouble(
                    netData.toLowerCase().replaceAll("mb", "").replaceAll(" ","")
            ));
        }
        else if (netData.toLowerCase().contains("bez limitu")) {
            offer.setInternetMbCost(new BigDecimal("0"));
        }
    }
}
