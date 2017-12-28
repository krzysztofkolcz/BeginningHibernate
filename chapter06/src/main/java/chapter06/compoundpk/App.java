package chapter06.compoundpk;

import chapter06.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        CPKBook cpkBook = new CPKBook();
        ISBN isbn = new ISBN();
        isbn.setCheckdigit(123);
        isbn.setGroup(1);
        isbn.setPublisher(2);
        isbn.setTitle(4);
        cpkBook.setId(isbn);
        cpkBook.setName("Title");

        session.save(cpkBook);
        tx.commit();
        session.close();


        session = SessionUtil.getSession();
        tx = session.beginTransaction();

        CPKBook cpkBook1 = (CPKBook) session.get(CPKBook.class,isbn);
        System.out.println(cpkBook1);

        tx.commit();
        session.close();
    }
}
