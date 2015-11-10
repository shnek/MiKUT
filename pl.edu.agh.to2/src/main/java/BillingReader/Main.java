import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin1 on 09.11.15.
 */
public class Main {

    static final int N = 19;

    public static void main(String[] args) throws IOException {

        List<Offer> list = new ArrayList<Offer>();

        Document doc = Jsoup.connect("http://super-sim.pl/porownanie-ofert").get();
        Elements offers = doc.select("tbody");
        offers.remove(0);

        for (Element e : offers) {
            Offer x = new Offer();
            Elements th = e.select("th");
            Elements p = e.select("p");

            if (th.isEmpty() || p.isEmpty()) break;

            x.setName(th.toString().replaceAll("<th>", "").replaceAll("</th>", ""));
            x.setMinutes(p.get(0).toString().replaceAll("<p>", "").replaceAll("</p>", ""));
            x.setSms(p.get(1).toString().replaceAll("<p>", "").replaceAll("</p>", ""));
            x.setInternet(Double.parseDouble(p.get(2).toString().replaceAll("<p>", "").replaceAll("</p>", "")
                .replaceAll(" GB","").replaceAll(",",".")));
            x.setMonths(Integer.parseInt(p.get(3).toString().replaceAll("<p>", "").replaceAll("</p>", "")));
            x.setAbonament(Double.parseDouble(p.get(4).toString().replaceAll("<p>", "")
                    .replaceAll("</p>", "").replaceAll("zł", "").replaceAll(",", ".")));
            //for (Element y : th) System.out.println(y.toString());
            //System.out.println(x.getMonths());

            list.add(x);
        }

        for (Offer o : list) System.out.println(o.toString());
    }

}
