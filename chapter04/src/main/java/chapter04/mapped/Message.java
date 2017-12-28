package chapter04.mapped;
import javax.persistence.*; 
@Entity(name="Message2")
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

  @Override
  public String toString() {
    String em = email == null ? " email is null ":", email is set " ;
    return "Message{" +
            "id=" + id +
            ", content='" + content + '\'' +
            em +
            '}';
  }
}
