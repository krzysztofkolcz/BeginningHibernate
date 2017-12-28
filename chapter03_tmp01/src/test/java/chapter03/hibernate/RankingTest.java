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
        //TODO - myślę, że lepiej wywalić zapisywanie do osobnych metod,
        //gdzie każda zawiera pojedyńczą transakcję.
        //do przemyślenia.
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Person object = savePerson(session,"Stefan");
        Person subject = savePerson(session,"Genowefa");
        Skill skill = saveSkill(session,"Java");
        Ranking ranking = new Ranking();
        ranking.setObserver(object);
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

    @Test
    public void rankingAvarage(){
      populateRankingData();
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      String name = "Stefan";
      String skill = "Java";
      int avg = getAverage(name,skill);
      System.out.println("Avg. ranking for " + name + " in " + skill + " is: " + avg);
      tx.commit();
      session.close();
      assertEquals(avg,7);
    }

    private int getAverage(String subjectName,String skillName){
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Ranking r where r.subject.name = :subjectName and r.skill.name = :skillName");
      q.setString("subjectName",subjectName);
      q.setString("skillName",skillName);
      int avg = 0;
      Integer sum = 0;
      int count = 0;
      for(Ranking r : (List<Ranking>)q.list()){
        System.out.println(r);
        count++;
        System.out.println(count);
        sum += r.getRanking();
        System.out.println(sum);
      }
      avg = sum / count;
      tx.commit();
      session.close();
      return avg;
    }

    private void populateRankingData(){
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      saveOneRakning(session, "Stefan","Marian","Java",8);
      saveOneRakning(session, "Stefan","Zenobia","Java",6);
      saveOneRakning(session, "Stefan","Ludwika","Java",7);
      tx.commit();
      session.close();
    }

    private void saveOneRakning(Session session, String subjectName,String objectName,String skillName,Integer rankingRate){
      Person object = savePerson(session,objectName);
      Person subject = savePerson(session,subjectName);
      Skill skill = saveSkill(session,skillName);
      Ranking ranking = new Ranking();
      ranking.setObserver(object);
      ranking.setSubject(subject);
      ranking.setSkill(skill);
      ranking.setRanking(rankingRate);
      session.save(ranking);
    }

    @Test
    public void changeRankingTest(){
      populateRankingData();
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Ranking r where r.subject.name = :subjectName and r.observer.name = :observerName and r.skill.name = :skillName");
      q.setString("subjectName","Stefan");
      q.setString("observerName","Ludwika");
      q.setString("skillName","Java");
      Ranking r = (Ranking)q.uniqueResult();
      r.setRanking(4);
      tx.commit();
      session.close();
      assertEquals(getAverage("Stefan","Java"),6);
    }

    @Test
    public void removeRankingTest(){
      populateRankingData();
      Session session = factory.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Ranking r where r.subject.name = :subjectName and r.observer.name = :observerName and r.skill.name = :skillName");
      q.setString("subjectName","Stefan");
      q.setString("observerName","Ludwika");
      q.setString("skillName","Java");
      Ranking r = (Ranking)q.uniqueResult();
      assertNotNull(r, "Ranking not found");
      session.delete(r);
      tx.commit();
      session.close();
      assertEquals(getAverage("Stefan","Java"),7);
    }
}

