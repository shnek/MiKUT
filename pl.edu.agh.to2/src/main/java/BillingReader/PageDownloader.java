package BillingReader;

import java.io.IOException;
import java.util.List;

public abstract class PageDownloader {

    private String url;
    private boolean abonament;

    public abstract List<Offer> download() throws IOException;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAbonament() {
        return abonament;
    }

    public void setAbonament(boolean abonament) {
        this.abonament = abonament;
    }
}
