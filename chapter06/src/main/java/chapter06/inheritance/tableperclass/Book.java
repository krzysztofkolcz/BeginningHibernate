package chapter06.inheritance.tableperclass;

import javax.persistence.*;

@Entity(name = "Book_TablePerClass")
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

