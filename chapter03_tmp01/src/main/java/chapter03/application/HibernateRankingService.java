package chapter03.application;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.*;
import com.kkolcz.hibernate.util.SessionUtil;
import chapter03.hibernate.*;
import java.util.List;
public class HibernateRankingService implements RankingService{

    @Override
    public int getRankingFor(String subject, String skill){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        int averate = getRankingFor(session,subject,skill);
        tx.commit();
        session.close();
        return averate;
    }

    private int getRankingFor(Session session,String subject, String skill){
        Query q = session.createQuery("from Ranking r where r.subject.name = :subject and r.skill.name = :skill");
        q.setString("name",subject);
        q.setString("skill",skill);
        List<Ranking> ranks = q.list();
        int count = 0;
        int sum = 0;
        for(Ranking r : ranks){
          sum += r.getRanking();
          count++; 
        }
        return count == 0 ? 0 : sum/count;
    }

    @Override
    public void addRanking(String subjectName, String objectName, String skillName, int rank){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        addRanking(session, subjectName, objectName, skillName, rank);
        tx.commit();
        session.close();
    }

    private void addRanking(Session session,String subjectName, String objectName, String skillName, int rank){
        Person object = savePerson(session,objectName);
        Person subject = savePerson(session,subjectName);
        Skill skill = saveSkill(session,skillName);
        Ranking ranking = new Ranking();
        ranking.setObject(object);
        ranking.setSubject(subject);
        ranking.setSkill(skill);
        ranking.setRanking(rank);
        session.save(rank);
    }

    @Override
    public void updateRanking(String subjectName,String objectName,String skillName,int rank){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Ranking r = findRanking(session,subjectName,objectName,skillName);
        if(r==null){
          addRanking(session, subjectName, objectName, skillName, rank);
        }else{
          r.setRanking(rank);
        }
        r.setRanking(rank);
        tx.commit();
        session.close();
    }

    private Ranking findRanking(Session session, String subjectName,String objectName,String skillName){
        Query q = session.createQuery("from Ranking r where r.subject.name = :subjectName and r.object.name = :objectName and r.skill.name = :skillName");
        q.setString("subjectName",subjectName);
        q.setString("objectName",objectName);
        q.setString("skillName",skillName);
        Ranking r = (Ranking)q.uniqueResult();
        return r;
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

    @Override
    public void removeRanking(String subject, String observer, String skill) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();

        removeRanking(session, subject, observer, skill);

        tx.commit();
        session.close();
    }

    private void removeRanking(Session session, String subject, String observer, String skill) {
        Ranking ranking = findRanking(session, subject, observer, skill);
        if (ranking != null) {
            session.delete(ranking);
        }
    }
}
