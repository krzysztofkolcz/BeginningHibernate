package chapter03.application;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ValidateRankingAverageTest{
    RankingService service = new HibernateRankingService();

    @Test
    public void validateRankingAverage() {
        service.addRanking("R1", "R2", "Skill", 1);
        service.addRanking("R1", "R3", "Skill", 3);
        service.addRanking("R1", "R4", "Skill", 5);
        int avg = service.getRankingFor("R1","Skill");
        assertEquals(avg,3);
        service.addRanking("R1", "R5", "Skill", 8);
        service.addRanking("R1", "R6", "Skill", 8);
        avg = service.getRankingFor("R1","Skill");
        assertEquals(avg,5);
    }


}
