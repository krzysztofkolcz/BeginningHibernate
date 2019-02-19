package chapter04.mapped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.util.Map;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import chapter04.util.SessionUtil;

/*
Sytuacja, gdzie jedna z Encji ma ustawione mappedBy w relacji.

@Entity(name="Message2")
public class Message {
...
  @OneToOne
  Email email;
...
}

@Entity(name="Email2")
public class Email {
  ...
  @OneToOne(mappedBy="email",fetch = FetchType.LAZY)
  Message message;
  ...
}

W tym przypadku po ustawieniu relacji z jednej strony:

message.setEmail(email);
session.save(email);
session.save(message);

Zapisaniu i ponownym odczycie z bazy,
zarówno message.getEmail() jak i email.getMessage() będą ustawione.
Przypadek, gdzie mappedBy nie jest ustawiona - chapter04.mapped.TestBrokenInversion
 */
public class TestImpliedInversion{

  @Test
  public void testImpliedRelationship() {
    Long emailId;
    Long messageId;
     
    Session session = SessionUtil.getSession();
    Transaction tx = session.beginTransaction();
     
    Email email = new Email("Inverse Email");
    Message message = new Message("Inverse Message");
     
    /* nie ustawione - email.setMessage(message); */
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
    //przed zapisaniem do bazy email.message - null
    //po zapisaniu do bazy to się zmieni.
    System.out.println(email);//Email{id=1, subject='Inverse Email' message is null }
    System.out.println(message);//Message{id=1, content='Inverse Message', email is set }

    session = SessionUtil.getSession();
    tx = session.beginTransaction();

    email = (Email) session.get(Email.class, emailId);
    message = (Message) session.get(Message.class, messageId);

    System.out.println(email);//Email{id=1, subject='Inverse Email' message is set }
    System.out.println(message);//Message{id=1, content='Inverse Message', email is set }
     
    tx.commit();
    session.close();
     
    assertNotNull(email.getMessage());
    assertNotNull(message.getEmail());
  }

}
