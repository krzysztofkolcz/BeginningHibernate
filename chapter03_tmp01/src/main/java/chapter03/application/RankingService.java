package chapter03.application;
import chapter03.hibernate.Person; 
import java.util.Map;
public interface RankingService {
    Person findBestForSkill(String skill);
    int getRankingFor(String subject, String skill);
    Map<String,Integer> getRankingsFor(String subject);
    void addRanking(String subject, String observer, String skill, int ranking);
    public void updateRanking(String subjectName,String objectName,String skillName,int rank);
    public void removeRanking(String subject, String observer, String skill); 
}
