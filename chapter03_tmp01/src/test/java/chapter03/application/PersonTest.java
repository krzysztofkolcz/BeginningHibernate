package chapter03.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;
import chapter03.hibernate.Person;

import java.util.List;

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
    String name ="J. C. Smell";
    PersonService personService = new PersonServiceImpl();
    personService.savePerson(name);


    List<Person> personList = personService.findAllPersons();
    Assert.assertEquals(1,personList.size());
  }

  //TODO - sprawdzić unique name constraint

  @Test
  public void findPersonTest(){
    String name = "C. J. Frank";
    PersonService personService = new PersonServiceImpl();
    personService.savePerson(name);
    Person foundPerson = personService.findPersonByName(name);
    Assert.assertEquals(foundPerson.getName(),name);
  }
}
