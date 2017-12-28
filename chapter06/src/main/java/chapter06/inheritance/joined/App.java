package chapter06.inheritance.joined;

import chapter06.util.*;
import org.hibernate.*;
import org.hibernate.Query;

public class App {

    public static void main(String[] args) {

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();

        Book book = new Book();
        book.setPages(10);
        book.setTitle("Book Title");

        BlogPost blogPost = new BlogPost();
        blogPost.setUrl("http://blogpost");
        blogPost.setTitle("Blog Title");

        BlogPost blogPost2 = new BlogPost();
        blogPost2.setUrl("http://blogpost_conana");
        blogPost2.setTitle("Conana Blog Title");

        session.save(book);
        session.save(blogPost);
        session.save(blogPost2);
        tx.commit();
        session.close();

    }
}
