package chapter03.application;
import chapter03.hibernate.Person; 
import java.util.Map;
public interface RankingService {
    Person findBestForSkill(String skill);
    Map<String,Integer> getRankingsFor(String subject);
    int getRankingFor(String subject, String skill);
    void addRanking(String subject, String observer, String skill, Integer ranking);
    public void updateRanking(String subjectName,String objectName,String skillName,int rank);
    public void removeRanking(String subject, String observer, String skill); 
}
