package chapter03.application;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AddRankingTest{
    RankingService service = new HibernateRankingService();

    @Test
    public void addRankingTest(){
      service.addRanking("Stefan","Genowefa","Mule",9);
      int rankingRate = service.getRankingFor("Stefan","Mule");
      assertEquals(rankingRate,9);
    }
}
