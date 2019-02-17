package chapter04.orphan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LibraryNoOrpahnRemoval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @OneToMany(orphanRemoval = false, mappedBy = "library")
    List<BookNoOrphanRemoval> books = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookNoOrphanRemoval> getBooks() {
        return books;
    }

    public void setBooks(List<BookNoOrphanRemoval> books) {
        this.books = books;
    }
}
