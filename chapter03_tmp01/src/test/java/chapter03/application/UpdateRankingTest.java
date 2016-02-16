package chapter03.application;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UpdateRankingTest{
    RankingService service = new HibernateRankingService();
    private String r1 = "asodgiahpoi r1"; 
    private String r2 = "xoaiga r2";
    private String s1 = "voais s1";

    @Test
    public void updateExistingRanking() {
        
        service.addRanking(r1, r2, s1, 6);
        assertEquals(service.getRankingFor(r1, s1), 6);
        service.updateRanking(r1, r2, s1, 7);
        assertEquals(service.getRankingFor(r1, s1), 7);
    }

    @Test
    public void updateNonexistentRanking() {
        assertEquals(service.getRankingFor(r2, s1), 0);
        service.updateRanking(r2, r1, s1, 7);
        assertEquals(service.getRankingFor(r2, s1), 7);
    }

}
