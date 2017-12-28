package com.mkyong.user;

import java.util.Date;
import org.hibernate.Session;
import com.mkyong.util.HibernateHsqldbUtil;

public class AppHsqldb {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + Hsqldb");
        Session session = HibernateHsqldbUtil.getSessionFactory().openSession();

        session.beginTransaction();
        DBUser user = new DBUser();

        user.setUserId(100);
        user.setUsername("superman");
        user.setCreatedBy("system");
        user.setCreatedDate(new Date());

        session.save(user);
        session.getTransaction().commit();
        HibernateHsqldbUtil.shutdown();
    }
}
