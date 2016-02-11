package chapter03.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PersonTest {
    /* SessionFactory factory; */
    /* @BeforeMethod */
    /* public void setUp(){ */
    /*   Configuration configuration = new Configuration(); */
    /*   configuration.configure(); */
    /*   ServiceRegistryBuilder srb = new ServiceRegistryBuilder(); */
    /*   srb.applySettings(configuration.getProperties()); */
    /*   ServiceRegistry sr  = srb.buildServiceRegistry(); */
    /*   factory = configuration.buildSessionFactory(sr); */
    /* } */
    /*  */
    /* @AfterMethod */
    /* public void tearDown(){ */
    /*   factory.close(); */
    /* } */
    /*  */
    /* @Test */
    /* public void savePersonTest(){ */
    /*   Session session = factory.openSession(); */
    /*   Transaction tx = session.beginTransaction(); */
    /*  */
    /*   Person person = new Person(); */
    /*   person.setName("Stefan"); */
    /*   session.save(person); */
    /*  */
    /*   tx.commit(); */
    /*   session.close(); */
    /* } */
}
