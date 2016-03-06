package com.websystique.hibernate;
 
import java.util.List;
 
import org.hibernate.Session;
 
import com.websystique.hibernate.model.Address;
import com.websystique.hibernate.model.Student;
 
public class HibernateStandAlone {
     
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        /* example001();  */
        example002(); 
    }

    public static void example001(){
        Student student = new Student("Sam","Disilva","Maths");
        Address address = new Address("10 Silver street","NYC","USA");
         
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        /*Look at how we have first persisted Student class , so that it’s id can be generated.*/
        session.persist(student);
         
        /*Then we have set the address id with student id(so that foreign key constraint can be respected).*/
        address.setId(student.getId());

        /* Finally we have set the address property of Student and saved student.
        Thanks to Cascade attribute on address property of Student class, 
        address will be saved automatically on student save.
        No need to save the address explicitly. */
        student.setAddress(address);
        session.save(student);
         
        List<Student> students = (List<Student>)session.createQuery("from Student ").list();
        for(Student s: students){
            System.out.println("Details : "+s);
        }
         
        session.getTransaction().commit();
        session.close();  
    }
 
    public static void example002(){
        Student student = new Student("Sam","Disilva","Maths");
        Address address = new Address("10 Silver street","NYC","USA");
         
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        /*Look at how we have first persisted Student class , so that it’s id can be generated.*/
        session.persist(student);
        System.out.println(student.getId());
         
         
        List<Student> students = (List<Student>)session.createQuery("from Student ").list();
        for(Student s: students){
            System.out.println("Details : "+s);
        }
         
        session.getTransaction().commit();
        session.close();  
    }
}
