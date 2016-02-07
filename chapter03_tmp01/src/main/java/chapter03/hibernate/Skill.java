package chapter03.hibernate;

import javax.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    public Skill() {}
    public void setId(Long id) { this.id=id; }
    public Long getId() { return id; }
    public void setName(String name) { this.name=name; }
    public String getName() { return name; }
}


