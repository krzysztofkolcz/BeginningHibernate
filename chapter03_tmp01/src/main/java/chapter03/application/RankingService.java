package chapter03.application;
 
public interface RankingService {
  int getRankingFor(String subject, String skill);
  void addRanking(String subject, String observer, String skill, int ranking);
    public void updateRanking(String subjectName,String objectName,String skillName,int rank);
}
