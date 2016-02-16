package chapter03.application;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.util.Map;

public class FindTest{
    RankingService service = new HibernateRankingService();

    @Test
    public void findNobodyTest(){
      int n = service.getRankingFor("Nobody","Nothing");
      int n2 = service.getRankingFor("Nobody","Nothing2");
      assertEquals(n,0);
      assertEquals(n2,0);
      Map<String,Integer>map = service.getRankingsFor("Nobody");
      assertEquals(map.size(),0);
    }

    @Test
    public void findRankingsTest(){
      int n = service.getRankingFor("Nobody","Nothing");
      int n2 = service.getRankingFor("Nobody","Nothing2");
      assertEquals(n,0);
      assertEquals(n2,0);
      Map<String,Integer>map = service.getRankingsFor("Nobody");
      assertEquals(map.size(),0);
    }
}
