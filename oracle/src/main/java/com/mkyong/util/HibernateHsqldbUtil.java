package com.mkyong.util;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateHsqldbUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            if (sessionFactory == null)
            {
                Configuration configuration = new Configuration();
                configuration.configure(HibernateHsqldbUtil.class.getResource("/hibernate-hsqldb.cfg.xml"));
//                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
                serviceRegistryBuilder.applySettings(configuration.getProperties());
//                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            return sessionFactory;
        } catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        getSessionFactory().close();
    }
}
