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


public class TestNoCascade {

    @Test
    public void testNoCascade() {
        Long emailId;
        Long messageId;

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Inverse Email");
        Message message = new Message("Inverse Message");

        email.setMessage(message);
//        message.setEmail(email);

        session.save(email);//jeżeli cascade nie ustawione, message nie zostanie zapisana automatycznie
//        session.save(message);

        emailId = email.getId();
        messageId = message.getId();

        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();
        email = (Email) session.get(Email.class, emailId);
        System.out.println(email);
//        message = (Message) session.get(Message.class, messageId);
        System.out.println(message);

        tx.commit();
        session.close();
    }

}
