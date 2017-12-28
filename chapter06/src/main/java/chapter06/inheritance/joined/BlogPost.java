package chapter06.inheritance.joined;

import javax.persistence.*;

@Entity(name = "BlogPost_Joined")
public class BlogPost extends Publication {
    @Column
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
