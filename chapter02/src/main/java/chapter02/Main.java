package chapter02;

import chapter02.hibernate.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Main{
  
  SessionFactory factory;
  public void setup() {
    Configuration configuration = new Configuration();
    configuration.configure();
    ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
    srBuilder.applySettings(configuration.getProperties());
    ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
    factory = configuration.buildSessionFactory(serviceRegistry);
  }

  public void saveMessage() {
    Message message = new Message("Hello, world");
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();
    session.persist(message);
    tx.commit();
    session.close();
  }

  public static void main(String args[]){
    Main main = new Main();
    main.setup();
    main.saveMessage(); 
  }
}
