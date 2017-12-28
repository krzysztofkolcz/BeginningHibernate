package chapter04.loading;

import chapter04.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import chapter04.mapped.*;
import org.hibernate.Hibernate;

public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Long emailId;
        Long messageId;

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Mapped");
        Message message = new Message("Mapped");

//        email.setMessage(message);
       message.setEmail(email);

        session.save(email);
        session.save(message);

        emailId = email.getId();
        messageId = message.getId();

        tx.commit();
        session.close();
        //transaction

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

        System.out.println(
            Hibernate.isInitialized(email.getMessage())
        );
        System.out.println(
            Hibernate.isPropertyInitialized(email.getMessage(),"content")
        );
        System.out.println(
            email.getMessage().getContent()
        );

      /* assertNotNull(email.getMessage()); */
      /* assertNull(message.getEmail()); */

    }

}
