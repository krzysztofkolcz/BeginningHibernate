package chapter06.inheritance.tableperclass;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Publication_TablePerClass")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Publication {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // Nie może być użyte przy tej strategii dziedziczenia
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column
    protected String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany(
            targetEntity = Author.class,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "Publication_TablePerClass_Author_TablePerClass",
            joinColumns = { @JoinColumn(name = "publicationId", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "authorId", referencedColumnName = "id") }
    )
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author){
        authors.add(author);
    }
}
