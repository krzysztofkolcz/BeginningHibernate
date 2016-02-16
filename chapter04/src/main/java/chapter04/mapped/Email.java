package chapter04.mapped;
import javax.persistence.*;
@Entity(name="Email2")
public class Email {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  String subject;
  
  @OneToOne(mappedBy="email")
  Message message;
   
  public Email() {
  }
   
  public Email(String subject) {
    setSubject(subject);
  }

  public void setMessage(Message message){
    this.message = message;
  }

  public Message getMessage(){
    return message;
  }

  public void setSubject(String subject){
    this.subject = subject;
  }

  public String getSubject(){
    return subject;
  }


  public void setId(Long id){
    this.id = id;
  }

  public Long getId(){
    return id;
  }
}
