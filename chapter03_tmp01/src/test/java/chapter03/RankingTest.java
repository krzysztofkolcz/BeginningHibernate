package chapter03.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RankingTest {
    SessionFactory factory;

    @BeforeMethod
    public void setUp(){
      System.out.println("SET UP");
      Configuration configuration = new Configuration();
      configuration.configure();
      ServiceRegistryBuilder srb = new ServiceRegistryBuilder();
      srb.applySettings(configuration.getProperties());
      ServiceRegistry sr = srb.buildServiceRegistry();
      factory = configuration.buildSessionFactory(sr);
    }

    @AfterMethod
    public void tearDown(){
      System.out.println("TEAR DOWN");
      factory.close();
    }

    @Test
    public void testSaveRanking() {
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Person object = savePerson(session,"Stefan");
      Person subject = savePerson(session,"Genowefa");
      Skill skill = saveSkill(session,"Java");
      Ranking ranking = new Ranking();
      ranking.setObject(object);
      ranking.setSubject(subject);
      ranking.setSkill(skill);
      ranking.setRanking(9);
      session.save(ranking);
      tx.commit();
      session.close();
    }

    private Person savePerson(Session session,String name){
      Person person = findPerson(session,name);
      if(person == null){
        person = new Person();
        person.setName(name);
        session.save(person);
      }
      return person;
    }

    private Person findPerson(Session session,String name){
      Query q = session.createQuery("from Person p where p.name = :name");
      q.setParameter("name",name);
      Person person = (Person)q.uniqueResult();
      return person;
    }

    private Skill saveSkill(Session session,String name){
      Skill skill = findSkill(session,name);
      if(skill == null){
        skill = new Skill();
        skill.setName(name);
        session.save(skill);
      }
      return skill;
    }

    private Skill findSkill(Session session,String name){
      Query q = session.createQuery("from Skill p where p.name = :name");
      q.setParameter("name",name);
      Skill skill = (Skill)q.uniqueResult();
      return skill;
    }
}

