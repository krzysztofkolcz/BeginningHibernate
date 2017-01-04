package chapter03.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import chapter03.hibernate.Person;
 
public class PersonTest {
  SessionFactory factory;
 
  @BeforeMethod
  public void setup() {
    Configuration configuration = new Configuration();
    configuration.configure();
    ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
    srBuilder.applySettings(configuration.getProperties());
    ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
    factory = configuration.buildSessionFactory(serviceRegistry);
  }
   
  @AfterMethod
  public void shutdown() {
    factory.close();
  }
   
  @Test
  public void testSavePerson() {
    Session session=factory.openSession();
    Transaction tx=session.beginTransaction();

    Person person=new Person();
    person.setName("J. C. Smell");
     
    session.save(person);
     
    tx.commit();
    session.close();
  }
}
