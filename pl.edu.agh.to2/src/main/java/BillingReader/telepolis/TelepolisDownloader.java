package BillingReader.telepolis;

import BillingReader.Offer;
import BillingReader.PageDownloader;
import BillingReader.telepolis.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public abstract class TelepolisDownloader extends PageDownloader {

    private String url;
    private boolean abonament;

    private NameSetter nameSetter;
    private OperatorSetter operatorSetter;
    private MonthlyPaymentSetter monthlyPaymentSetter;
    private List<AttributeSetter> fieldSetters;

    public TelepolisDownloader() {
        fieldSetters = new ArrayList<AttributeSetter>();
        nameSetter = new NameSetter();
        operatorSetter = new OperatorSetter();
        monthlyPaymentSetter = new MonthlyPaymentSetter();
        fieldSetters.add(new InnerCallSetter());
        fieldSetters.add(new OuterCallSetter());
        fieldSetters.add(new InnerSmsSetter());
        fieldSetters.add(new OuterSmsSetter());
        fieldSetters.add(new InnerMmsSetter());
        fieldSetters.add(new OuterSmsSetter());
        fieldSetters.add(new InternetFieldsSetter());

        this.url = "http://www.telepolis.pl/na-karte";
        this.abonament = true;
    }

    public List<Offer> download () throws IOException {
        List<Offer> list = new LinkedList<Offer>();
        List<String> links = new LinkedList<String>();

        Document doc = Jsoup.connect(url).get();
        Elements offers = doc.select("li.gsmWynik ");

        for (Element elem : offers) {
            Offer nextOffer = new Offer();
            boolean inputCorrectFlag = true;

            nameSetter.setAttribute(nextOffer,elem);
            operatorSetter.setAttribute(nextOffer,elem);
            monthlyPaymentSetter.setAttribute(nextOffer,elem);
            nextOffer.setAbonament(abonament);

            Element tmp = elem.select("a").first();
            String link = tmp.attr("abs:href");
            links.add(link);
            Document doc2 = Jsoup.connect(link).get();
            Elements cells = doc2.select("td");
            Iterator<Element> cell = cells.iterator();

            try {
                while (cell.hasNext()) {
                    Element next1 = cell.next();
                    Element next2 = cell.next();
                    for (AttributeSetter setter : fieldSetters) {
                        if (setter.matchesPattern(String.valueOf(next1)))
                            setter.setAttribute(nextOffer, next2);
                    }
                }
            } catch (NumberFormatException e) {
                //inputCorrectFlag = false;
            } catch (NullPointerException f) {
                //inputCorrectFlag = false;
            } catch (NoSuchElementException g) {
                break;
            }

            if (inputCorrectFlag) {
                list.add(nextOffer);
                out("Dodano oferte " + nextOffer.getName());
            }
        }
        return list;
    }

    private void out (String line)
    {
        System.out.println(line);
    }
}
