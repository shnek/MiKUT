import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin1 on 27.11.15.
 */
public class TelepolisDownloader {

    public List<Offer> download (String url, boolean abonament) throws IOException {
        List<Offer> list = new LinkedList<Offer>();
        List<String> links = new LinkedList<String>();

        Document doc = Jsoup.connect(url).get();
        Elements offers = doc.select("li.gsmWynik ");

        int counter = 0;
        for (Element elem : offers)
        {
            Offer x = new Offer();
            boolean inputCorrectFlag = true;
            double x1, x2;

            x.setOperator(Operator.findByName(String.valueOf(elem.select("span[itemprop=brand]").first())
                            .replaceAll("<span itemprop=\"brand\">", "")
                            .replaceAll("</span>", ""))
            );

            if (x.getOperator() == null) continue;

            x.setName(String.valueOf(elem.select("span[itemprop=name]").first())
                            .replaceAll("<span itemprop=\"name\">", "")
                            .replaceAll("</span>", "")
            );

            x.setMonthlyPayment(new BigDecimal(
                    String.valueOf(elem.select("span[itemprop=price]").first())
                            .replaceAll("<span itemprop=\"price\">", "")
                            .replaceAll("</span>", "")
                            .replaceAll(",",".")
            ));

            x.setAbonament(abonament);

            Element tmp = elem.select("a").first();
            String link = tmp.attr("abs:href");
            links.add(link);

            Document doc2 = Jsoup.connect(link).get();
            Elements cells = doc2.select("td");

            int idx = 0;
            String netData;

            try
            {
                while (idx < cells.size())
                {
                    String pattern = String.valueOf(cells.get(idx));
                    if (pattern.contains("Koszt minuty do własnej sieci"))
                    {
                        x.setInnerCallCost(parseField(cells.get(++idx)));
                        idx++;
                    }
                    else if (pattern.contains("Opłata za SMS do własnej sieci"))
                    {
                        x.setInnerSmsCost(parseField(cells.get(++idx)));
                        idx++;
                    }
                    else if (pattern.contains("Opłata za SMS do pozostałych sieci"))
                    {
                        x.setOuterSmsCost(parseField(cells.get(++idx)));
                        idx++;
                    }
                    else if (pattern.contains("Opłata za MMS do własnej sieci"))
                    {
                        x.setInnerMmsCost(parseField(cells.get(++idx)));
                        idx++;
                    }
                    else if (pattern.contains("Opłata za MMS do pozostałych sieci"))
                    {
                        x.setOuterMmsCost(parseField(cells.get(++idx)));
                        idx++;
                    }
                    else if (pattern.contains("Koszt minuty do pozostałych sieci"))
                    {
                        x.setOuterCallCost(parseField(cells.get(++idx)));
                        idx++;
                    }
                    else if (pattern.contains("Pakiet internetowy"))
                    {
                        netData = String.valueOf(cells.get(++idx));
                        if (netData.contains("colspan"))
                        {
                            String tmp1 = netData.split(">")[1];
                            String tmp2 = tmp1.split(";")[0];
                            String[] netInfo = tmp2.split("/|\\(");
                            x1 = Double.parseDouble(
                                    netInfo[0].replaceAll(",",".").replaceAll(" zł","")
                            );
                            if (netInfo[1].toLowerCase().contains("kb")) {
                                x2 = Double.parseDouble(
                                        netInfo[1].replaceAll("kB","").replaceAll(" ","")
                                );
                                x.setInternetMbCost(new BigDecimal(String.valueOf((1000/x2)*x1)));
                            }
                            else if (netInfo[1].toLowerCase().contains("mb")) {
                                x2 = Double.parseDouble(
                                        netInfo[1].replaceAll("MB","").replaceAll(" ","")
                                );
                                x.setInternetMbCost(new BigDecimal(String.valueOf((1000/x2)*x1)));
                            }
                            else {
                                inputCorrectFlag = false;
                            }
                        }
                        else
                        {
                            netData = netData.split("<|>")[2];
                            if (netData.toLowerCase().contains("gb")) {
                                x.setFreeInternetMb(1000 * Double.parseDouble(
                                        netData.replaceAll(" GB", "")
                                ));
                            }
                            else if (netData.toLowerCase().contains("mb")) {
                                x.setFreeInternetMb(Double.parseDouble(
                                        netData.replaceAll(" MB", "")
                                ));
                            }
                            else if (netData.toLowerCase().contains("bez limitu")) {
                                x.setInternetMbCost(new BigDecimal("0"));
                            }
                            else {
                                inputCorrectFlag = false;
                            }
                        }
                        if ((x.getFreeInternetMb() == 0) && (x.getInternetMbCost() == null))
                            inputCorrectFlag = false;
                        idx++;
                    }
                    else
                        idx++;
                }
            }
            catch (NumberFormatException | NullPointerException e)
            {
                inputCorrectFlag = false;
            }

            if (inputCorrectFlag) {
                list.add(x);
                out("Dodano oferte "+x.getName());
            }

        }

        return list;
    }

    private BigDecimal parseField (Element e)
    {
       return new BigDecimal(
                String.valueOf(e)
                        .replaceAll("bez limitu","0")
                        .replaceAll("<td>","")
                        .replaceAll("</td>","")
                        .replaceAll(" zł","")
                        .replaceAll(",",".")
       );
    }

    private void out (String line)
    {
        System.out.println(line);
    }
}
