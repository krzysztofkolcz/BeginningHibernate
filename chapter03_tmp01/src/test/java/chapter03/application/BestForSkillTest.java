package chapter03.application;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import chapter03.hibernate.Person;

public class BestForSkillTest{
    RankingService service = new HibernateRankingService();

    private String r1 = "Gene Showrama"; 
    private String r2 = "Scottball Most";
    private String r3 = "Bla blabla";
    private String r4 = "Dupa jasiu";
    private String r5 = "Twoja stara";
    private String s1 = "s1"; 
    private String s2 = "s2";
    private String s3 = "s3";
    private String s4 = "s4";
    private String s5 = "s5";
    private String skill = "Ceylon";
    private String skill2 = "java";

    @Test
    public void findBestForNonexistingSkill(){
      Person p = service.findBestForSkill("no skill");
      assertNull(p);
    }

    @Test
    public void findBestForSkill(){
        service.addRanking(r1, s1, skill, 6);
        service.addRanking(r1, s2, skill, 9);/*7,5*/

        service.addRanking(r2, s1, skill, 4);
        service.addRanking(r2, s2, skill, 2);/*3*/

        service.addRanking(r3, s1, skill, 8);
        service.addRanking(r3, s2, skill, 9);
        service.addRanking(r3, s3, skill, 9);/*8,66*/

        service.addRanking(r4, s1, skill, 9);
        service.addRanking(r4, s2, skill, 1);/*5*/

        service.addRanking(r5, s1, skill, 1);/*1*/

        service.addRanking(r5, s1, skill2, 1);

        Person p =service.findBestForSkill(skill);
        assertEquals(p.getName(), r3);
    }

}
