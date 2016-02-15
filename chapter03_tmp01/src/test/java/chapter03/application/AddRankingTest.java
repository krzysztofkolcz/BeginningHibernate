package chapter03.application;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AddRankingTest{
    RankingService service = new HibernateRankingService();

    @Test
    public void addRankingTest(){
        System.out.println("ADD RANKING TEST");
      service.addRanking("Stefan","Genowefa","Mule",new Integer(9));
      int rankingRate = service.getRankingFor("Stefan","Mule");
      assertEquals(rankingRate,9);
    }
}
