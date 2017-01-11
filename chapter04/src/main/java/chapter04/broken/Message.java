package chapter04.broken;
import javax.persistence.*; 
@Entity
@Table(name="message_broken")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
   
  @Column
  String content;
   
  @OneToOne
  Email email;
   
  public Message() {
  }
   
  public Message(String content) {
    setContent(content);
  }

  public void setEmail(Email email){
    this.email = email;
  }

  public Email getEmail(){
    return email;
  }

  public void setContent(String content){
    this.content = content;
  }

  public String getContent(){
    return content;
  }


  public void setId(Long id){
    this.id = id;
  }

  public Long getId(){
    return id;
  }
}
