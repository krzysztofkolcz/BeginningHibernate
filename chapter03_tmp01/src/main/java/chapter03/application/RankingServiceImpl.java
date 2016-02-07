package chapter03.application;

import com.kkolcz.hibernate.util.SessionUtil;
 
public class RankingServiceImpl implements RankingService{

    @Override
    public int getRankingFor(String subject, String skill){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        Query q = session.createQuery("from Ranking r where r.subject.name = :subject and r.skill.name = :skill");
        List<Ranking> ranks = q.list();
        int avg = 0;
        int count = 0;
        int sum = 0;
        for(Ranking r : ranks){
          sum += r.getRanking();
          count++; 
        }
        count == 0 ? avg = 0 : avg = sum/count;
        tx.commit();
        session.close();
        return avg;
    }

    @Override
    public void addRanking(Session session,String subjectName, String objectName, String skillName, int rank){
        
        Person object = savePerson(session,ovserverName);
        Person subject = savePerson(session,subjectName);
        Skill skill = saveSkill(session,skillName);
        Ranking ranking = new Ranking();
        ranking.setObject(object);
        ranking.setSubject(subject);
        ranking.setSkill(skill);
        ranking.setRanking();
        session.save(rank);

    }

    @Override
    public void updateRanking(String subjectName,String objectName,String skillName,int rank){
        Session session = factory.openSession();
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
        assertEquals(getAverage("Stefan","Java"),6);
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

}
