package chapter03.application;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AddRankingTest{
    RankingService service = new HibernateRankingService();

    @Test
    public void addRankingTest(){
      service.addRanking("Stefan","Genowefa","Java",9);
      int rankingRate = service.getRankingFor("Stefan","Java");
      assertEquals(rankingRate,8);
    }
}
