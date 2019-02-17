package chapter04.orphan;

import chapter04.util.SessionUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.testng.annotations.Test;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class OrphanRemovalTest {

    @Test
    public void orphanRemovalTest() {
        Long id = createLibrary();

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Library library = (Library) session.load(Library.class, id);
        assertEquals(library.getBooks().size(), 3);

        library.getBooks().remove(0);
        assertEquals(library.getBooks().size(), 2);

        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();

        Library l2 = (Library) session.load(Library.class, id);
        assertEquals(l2.getBooks().size(), 2);

        Query query = session.createQuery("from Book b");
        List books = query.list();
        assertEquals(books.size(), 2);

        tx.commit();
        session.close();
    }

    private Long createLibrary() {

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        Library library = new Library();
        library.setName("orphanLib");
        session.save(library);

        Book book = new Book();
        book.setLibrary(library);
        book.setTitle("book 1");
        session.save(book);
        library.getBooks().add(book);

        book = new Book();
        book.setLibrary(library);
        book.setTitle("book 2");
        session.save(book);
        library.getBooks().add(book);

        book = new Book();
        book.setLibrary(library);
        book.setTitle("book 3");
        session.save(book);
        library.getBooks().add(book);

        tx.commit();
        session.close();
        return library.getId();
    }

    @Test
    public void orphanRemovalTest_NoOrphanRemovalFlag() {
        Long id = createLibraryNoOrphanRemoval_NoOrphanRemovalFlag();

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        LibraryNoOrpahnRemoval library = (LibraryNoOrpahnRemoval) session.load(LibraryNoOrpahnRemoval.class, id);
        assertEquals(library.getBooks().size(), 3);

        library.getBooks().remove(0);
        //Bez orphan removal ta opcja nie propaguje się do bazy.
        assertEquals(library.getBooks().size(), 2);

        tx.commit();
        session.close();

        session = SessionUtil.getSession();
        tx = session.beginTransaction();

        LibraryNoOrpahnRemoval l2 = (LibraryNoOrpahnRemoval) session.load(LibraryNoOrpahnRemoval.class, id);
        assertEquals(l2.getBooks().size(), 3);

        Query query = session.createQuery("from Book b");
        List books = query.list();
        assertEquals(books.size(), 3);

        tx.commit();
        session.close();
    }

    private Long createLibraryNoOrphanRemoval_NoOrphanRemovalFlag() {

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        LibraryNoOrpahnRemoval library = new LibraryNoOrpahnRemoval();
        library.setName("orphanLib");
        session.save(library);

        BookNoOrphanRemoval book = new BookNoOrphanRemoval();
        book.setLibrary(library);
        book.setTitle("book 1");
        session.save(book);
        library.getBooks().add(book);

        book = new BookNoOrphanRemoval();
        book.setLibrary(library);
        book.setTitle("book 2");
        session.save(book);
        library.getBooks().add(book);

        book = new BookNoOrphanRemoval();
        book.setLibrary(library);
        book.setTitle("book 3");
        session.save(book);
        library.getBooks().add(book);

        tx.commit();
        session.close();
        return library.getId();
    }
}
