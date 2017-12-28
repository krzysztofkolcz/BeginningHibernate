package chapter06.inheritance.mappedsuperclass;

import chapter06.util.SessionUtil;
import org.hibernate.*;
import javax.persistence.*;

@Entity
public class App {
    public static void main(String[] args) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Book book = new Book();
        book.setPages(10);
        book.setTitle("Book Title");

        BlogPost blogPost = new BlogPost();
        blogPost.setUrl("http://blogpost");
        blogPost.setTitle("Blog Title");

        session.save(book);
        tx.commit();
        session.close();
    }
}
