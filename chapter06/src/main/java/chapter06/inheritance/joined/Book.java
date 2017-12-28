package chapter06.inheritance.joined;

import javax.persistence.*;

@Entity(name = "Book_Joined")
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
