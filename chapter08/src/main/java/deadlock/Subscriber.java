package deadlock;

import javax.persistence.*;

@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String username;

    public Subscriber() {
    }

    public Subscriber(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
