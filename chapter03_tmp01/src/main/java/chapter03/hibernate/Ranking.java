package chapter03.hibernate;
import javax.persistence.*;
@Entity
public class Ranking{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column
  int ranking;
  @ManyToOne
  Person object;
  @ManyToOne
  Person subject;
  @ManyToOne
  Skill skill;
  public Ranking() {}
  public void setId(Long id) { this.id=id; }
  public Long getId() { return id; }
  public void setRanking(int ranking) { this.ranking=ranking; }
  public int getRanking() { return ranking; }
  public void setObject(Person object) { this.object=object; }
  public Person getObject() { return object; }
  public void setSubject(Person subject) { this.subject=subject; }
  public Person getSubject() { return subject; }
  public void setSkill(Skill skill) { this.skill=skill; }
  public Skill getSkill() { return skill; }
}
