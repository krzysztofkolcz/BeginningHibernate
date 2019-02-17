package chapter04.orphan;

import javax.persistence.*;

@Entity
public class BookNoOrphanRemoval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String title;
    @ManyToOne
    LibraryNoOrpahnRemoval library;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LibraryNoOrpahnRemoval getLibrary() {
        return library;
    }

    public void setLibrary(LibraryNoOrpahnRemoval library) {
        this.library = library;
    }
}
