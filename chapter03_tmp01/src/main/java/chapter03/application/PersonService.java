package chapter03.application;

import chapter03.hibernate.Person;
import org.hibernate.Session;

import java.util.List;

public interface PersonService {

    public Person findPersonByName(String name);
    public void savePerson(String name);
    public List<Person> findAllPersons();
}
