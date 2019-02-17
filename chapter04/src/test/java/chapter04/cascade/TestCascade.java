package chapter04.cascade;

import chapter04.cascade.Email;
import chapter04.cascade.Message;
import chapter04.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;


public class TestCascade {

    @Test
    public void testCascade() {
        Long emailId;
        Long messageId;

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Inverse Email");
        Message message = new Message("Inverse Message");

        email.setMessage(message);//Należy ustawić message w email
        message.setEmail(email);//Oraz email w message.

        session.save(email);//jeżeli cascade ustawione na ALL lub PERSIST, message zostanie zapisana automatycznie
//        session.save(message);

        emailId = email.getId();
//        messageId = message.getId();

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
