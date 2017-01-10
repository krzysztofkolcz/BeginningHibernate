package chapter04.broken;
 
import java.util.List;
import chapter04.util.SessionUtil;
 
import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class App{
 
    @SuppressWarnings("unused")
    public static void main(String[] args) { 
      Long emailId;
      Long messageId;
       
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();
       
      Email email = new Email("Broken");
      Message message = new Message("Broken");
       
      email.setMessage(message);
      // message.setEmail(email);

      session.save(email);
      session.save(message);

      emailId = email.getId();
      messageId = message.getId();

      tx.commit();
      session.close();

      /* assertNotNull(email.getMessage()); */
      /* assertNull(message.getEmail()); */

      session = SessionUtil.getSession();
      tx = session.beginTransaction();
      email = (Email) session.get(Email.class, emailId);
      System.out.println(email);
      message = (Message) session.get(Message.class, messageId);
      System.out.println(message);
      tx.commit();
      session.close();

      /* assertNotNull(email.getMessage()); */
      /* assertNull(message.getEmail()); */
 
    }
 
}
