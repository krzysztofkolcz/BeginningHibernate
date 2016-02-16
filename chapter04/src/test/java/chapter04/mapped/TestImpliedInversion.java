package chapter04.mapped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.*;
import com.kkolcz.hibernate.util.SessionUtil;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.util.Map;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class TestImpliedInversion{

  @Test
  public void testImpliedRelationship() {
    Long emailId;
    Long messageId;
     
    Session session = SessionUtil.getSession();
    Transaction tx = session.beginTransaction();
     
    Email email = new Email("Inverse Email");
    Message message = new Message("Inverse Message");
     
    /* email.setMessage(message); */
    message.setEmail(email);

    session.save(email);
    session.save(message);

    emailId = email.getId();
    messageId = message.getId();
    tx.commit();
    session.close();
     
    assertEquals(email.getSubject(), "Inverse Email");
    assertEquals(message.getContent(), "Inverse Message");
    assertNull(email.getMessage());
    assertNotNull(message.getEmail());
     
    session = SessionUtil.getSession();
    tx = session.beginTransaction();
    email = (Email) session.get(Email.class, emailId);
    System.out.println(email);
    message = (Message) session.get(Message.class, messageId);
    System.out.println(message);
     
    tx.commit();
    session.close();
     
    assertNotNull(email.getMessage());
    assertNotNull(message.getEmail());
  }

}
