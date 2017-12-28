package chapter06.inheritance.tableperclass;

import chapter06.util.SessionUtil;
import org.hibernate.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class App {
    public static void main(String[] args) {

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

//        Long count = (Long) session.createQuery("Select count(*) from Author_TablePerClass a").uniqueResult();
//        System.out.println("Start Script =====> Authors size count(*):"+count);
//        Query query = session.createSQLQuery("alter table Publication_TablePerClass_Author_TablePerClass drop constraint FK_5hiufouhmlpkgk7pjmci3n0j4");
//        query.executeUpdate();
        Query query = session.createSQLQuery("Delete from Author_TablePerClass");
        query.executeUpdate();

        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();

        Author author = new Author();
        author.setFirstName("Jan");
        author.setLastName("Bakowski");

        Author author2 = new Author();
        author2.setFirstName("Kanon");
        author2.setLastName("Barabararzyńa");

        Book book = new Book();
        book.setPages(10);
        book.setTitle("Book Title");
        book.addAuthor(author);

        BlogPost blogPost = new BlogPost();
        blogPost.setUrl("http://blogpost");
        blogPost.setTitle("Blog Title");
        blogPost.addAuthor(author);

        BlogPost blogPost2 = new BlogPost();
        blogPost2.setUrl("http://blogpost_conana");
        blogPost2.setTitle("Conana Blog Title");
        blogPost2.addAuthor(author2);

//        session.save(author); //niepotrzebne - cascade.ALL
        session.save(book);
        session.save(blogPost);
        session.save(blogPost2);
        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();

//        Long count2 = (Long) session.createQuery("Select count(*) from Author_TablePerClass a").uniqueResult();
//        System.out.println("Authors size count(*):"+count2);

//        Long bookcount = (Long) session.createQuery("Select count(*) from Book_TablePerClass a").uniqueResult();
//        System.out.println("Book size count(*):"+bookcount);

//        Long blogcount = (Long) session.createQuery("Select count(*) from BlogPost_TablePerClass a").uniqueResult();
//        System.out.println("Blog size count(*):"+blogcount);

        List<Author> authors = session.createQuery("SELECT a FROM Author_TablePerClass a").list();
//        System.out.println("Authors size:"+authors.size());
        for (Author a : authors) {
            System.out.println("Author:"+a.getFirstName()+ " "+ a.getLastName());
            System.out.println("Publication number:"+a.getPublications().size());
            for (Publication p : a.getPublications()) {
                if (p instanceof Book)
                    System.out.println(p.getTitle()+ " book");
                else
                    System.out.println(p.getTitle()+ " blog post");
            }
        }
//        session.createSQLQuery("DROP SCHEMA PUBLIC CASCADE").executeUpdate();

        tx.commit();
        session.close();
    }
}
