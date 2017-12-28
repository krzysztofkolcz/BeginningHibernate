package chapter03.application;

import chapter03.hibernate.Person;
import chapter03.util.SessionUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public void savePerson(String name) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        if(findPersonByName(session,name) == null) {
            Person person = new Person();
            person.setName(name);
            session.save(person);
        }
        tx.commit();
        session.close();
    }

    @Override
    public Person findPersonByName(String name) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Person person = findPersonByName(session,name);
        tx.commit();
        session.close();
        return person;
    }

    private Person findPersonByName(Session session,String name){
        Query query=session.createQuery("from Person p where p.name=:name");
        query.setParameter("name", name);
        Person person = (Person) query.uniqueResult();
        return person;
    }

    @Override
    public List<Person> findAllPersons() {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        List<Person> personList = findAllPersons(session);
        tx.commit();
        session.close();
        return personList;
    }

    private List<Person> findAllPersons(Session session){
        Query query=session.createQuery("from Person p");
        List<Person> personList =  query.list();
        return personList;
    }
}
