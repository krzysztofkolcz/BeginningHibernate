package chapter06.inheritance.mappedsuperclass;

import javax.persistence.*;

@Entity(name = "Book")
public class Book extends Publication {

    @Column
    private int pages;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

