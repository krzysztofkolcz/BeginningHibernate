package chapter03.application;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.*;
import com.kkolcz.hibernate.util.SessionUtil;
import chapter03.hibernate.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class HibernateRankingService implements RankingService{

    @Override
    public Person findBestForSkill(String skill){
      Person person = null;
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("select r.subject.name,avg(r.ranking) from Ranking r where r.skill.name = :skillName group by r.subject.name order by avg(r.ranking) desc");
      q.setParameter("skillName",skill);
      List<Object[]> result = q.list();
      if(result.size()>0){
          person = findPerson(session, (String)result.get(0)[0]);
      }
      tx.commit();
      session.close();
      return person;
    }

    @Override
    public Map<String,Integer> getRankingsFor(String subject){
      Session session = SessionUtil.getSession();
      Transaction tx = session.beginTransaction();
      Map<String,Integer> rankings = new HashMap<>(); 
      Query q = session.createQuery("from Ranking r where r.subject.name = :name order by r.skill.name");
      q.setString("name",subject);
      List<Ranking> list= q.list();
      String lastSkillName = "";
      int sum = 0;
      int count = 0;
      for(Ranking r : list){
        if(!lastSkillName.equals(r.getSkill().getName())){
          sum = 0;
          count = 0;
          lastSkillName = r.getSkill().getName();
        }
        sum += r.getRanking();
        count++;
        rankings.put(lastSkillName,sum/count);
      }
      tx.commit();
      session.close();
      return rankings;
    }

    @Override
    public int getRankingFor(String subject, String skill){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        int average = getRankingFor(session,subject,skill);
        tx.commit();
        session.close();
        return average;
    }

    private int getRankingFor(Session session,String subject, String skill){
        Query q = session.createQuery("from Ranking r where r.subject.name = :subject and r.skill.name = :skill");
        q.setString("subject",subject);
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
        session.save(ranking);
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
