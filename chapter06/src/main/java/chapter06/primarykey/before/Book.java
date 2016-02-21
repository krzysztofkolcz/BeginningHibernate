package chapter06.primarykey.before;
import javax.persistence.*;

@Entity
public class Book {
  String title;
  int pages;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  int id;
   
  public String getTitle() {
    return title;
  }
   
  public void setTitle(String title) {
    this.title = title;
  }
   
  public int getPages() {
    return pages;
  }
   
  public void setPages(int pages) {
    this.pages = pages;
  }
  public int getId() {
    return id;
  }
   
  public void setId(int id) {
    this.id = id;
  }
}
