package chapter07;

import javax.persistence.*;

@Entity
public class Thing {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   Integer id;

   @Column
   private String name;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Thing)) return false;

      Thing thing = (Thing) o;

      if (getId() != null ? !getId().equals(thing.getId()) : thing.getId() != null) return false;
      return getName() != null ? getName().equals(thing.getName()) : thing.getName() == null;
   }

   @Override
   public int hashCode() {
      int result = getId() != null ? getId().hashCode() : 0;
      result = 31 * result + (getName() != null ? getName().hashCode() : 0);
      return result;
   }
}