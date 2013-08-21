package bookmarktable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bookmark {
    StringProperty site = new SimpleStringProperty();
    StringProperty url = new SimpleStringProperty();
    
    public Bookmark(String site, String url) {
        this.site.set(site);
        this.url.set(url);
    }
    
    public StringProperty siteProperty() {
        return site;
    }
    
    public StringProperty urlProperty() {
        return url;
    }

    public String getSite() {
        return site.get();
    }

    public void setSite(String site) {
        this.site.set(site);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }
}
