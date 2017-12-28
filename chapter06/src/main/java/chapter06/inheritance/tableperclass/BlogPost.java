package chapter06.inheritance.tableperclass;

import javax.persistence.*;

@Entity(name = "BlogPost_TablePerClass")
public class BlogPost extends Publication {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column
    private String url;
}
