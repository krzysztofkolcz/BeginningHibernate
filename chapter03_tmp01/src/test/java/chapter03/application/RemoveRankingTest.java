package chapter03.application;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class RemoveRankingTest{
    RankingService service = new HibernateRankingService();

    @Test
    public void removeRanking() {
        service.addRanking("R1", "R2", "RS1", 6);
        assertEquals(service.getRankingFor("R1", "RS1"), 6);
        service.removeRanking("R1", "R2", "RS2");
        assertEquals(service.getRankingFor("R1", "RS1"), 0);
    }

    @Test
    public void removeNonexistentRanking() {
        service.removeRanking("R3", "R5", "RS3");
    }

}
