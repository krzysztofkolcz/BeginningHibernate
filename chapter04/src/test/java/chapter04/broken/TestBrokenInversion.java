package chapter04.broken;

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
W tym przypadku ani klasa Email ani Message nie mają ustawionego pola mappedBy.
Czyli
Email{
...
  @OneToOne
  Message message;
}

Message{
...
  @OneToOne
  Email email;
}

Oznacza to, że Hibernate wygeneruje sqla, gdzie obydwie tabele będą miały klucz obcy.

Utworzenie objektów i ustawienie tylko w jednym:

email.setMessage(message);
// brak ustawienia - message.setEmail(email);
session.save(email);
session.save(message);

spowoduje, że po ponownym pobraniu z bazy
message.getEmail() - będzie null

Przypadek, gdzie mappedBy jest ustawiona - chapter04.mapped.TestImpliedInversion
 */
public class TestBrokenInversion{

  @Test()
  public void testBrokenInversionCode() {
    Long emailId;
    Long messageId;
     
    Session session = SessionUtil.getSession();
    Transaction tx = session.beginTransaction();
     
    Email email = new Email("Broken");
    Message message = new Message("Broken");
     
    email.setMessage(message);
    // brak ustawienia - message.setEmail(email);

    session.save(email);
    session.save(message);

    emailId = email.getId();
    messageId = message.getId();

    tx.commit();
    session.close();

    assertNotNull(email.getMessage());
    assertNull(message.getEmail());

    session = SessionUtil.getSession();
    tx = session.beginTransaction();
    email = (Email) session.get(Email.class, emailId);
    System.out.println(email);
    message = (Message) session.get(Message.class, messageId);
    System.out.println(message);
    tx.commit();
    session.close();

    assertNotNull(email.getMessage());
    assertNull(message.getEmail());

  }

  @Test
  public void testProperSimpleInversionCode() {
    Long emailId;
    Long messageId;
     
    Session session = SessionUtil.getSession();
    Transaction tx = session.beginTransaction();
     
    Email email = new Email("Proper");
    Message message = new Message("Proper");
     
    email.setMessage(message);
    message.setEmail(email);
     
    session.save(email);
    session.save(message);
     
    emailId = email.getId();
    messageId = message.getId();
     
    tx.commit();
    session.close();
     
    assertNotNull(email.getMessage());
    assertNotNull(message.getEmail());
     
    session = SessionUtil.getSession();
    tx = session.beginTransaction();
    email = (Email) session.get(Email.class, emailId);
    System.out.println(email);
    message = (Message) session.get(Message.class, messageId);
    System.out.println(message);
  }

}
