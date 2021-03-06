package chapter03.hibernate;

import javax.persistence.*;

@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  public Person() {}
  public Long getId() { return id; }

  public void setName(String name) { this.name=name; }
  public String getName() { return name; }

  public String toString(){
    return "Name:" + name;
  }

}
