package chapter07.lifecycle;

import chapter07.util.JPASessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LifecycleTest {

    @Test
    public void testLifecycle() {
        Integer id;
        Session session = JPASessionUtil.getSession("chapter07");
        Transaction tx = session.beginTransaction();
        LifecycleThing thing1 = new LifecycleThing();
        thing1.setName("Thing 1");
        session.save(thing1);
        id = thing1.getId();
        tx.commit();
        session.close();
        session = JPASessionUtil.getSession("chapter07");
        tx = session.beginTransaction();
        LifecycleThing thing2 = (LifecycleThing) session
                .byId(LifecycleThing.class)
                .load(-1);
        Assert.assertNull(thing2);
        thing2 = (LifecycleThing) session.byId(LifecycleThing.class)
                .getReference(id);
        Assert.assertNotNull(thing2);
        Assert.assertEquals(thing1, thing2);
        thing2.setName("Thing 2");
        tx.commit();
        session.close();
        session = JPASessionUtil.getSession("chapter07");
        tx = session.beginTransaction();
        LifecycleThing thing3 = (LifecycleThing) session
                .byId(LifecycleThing.class)
                .getReference(id);
        Assert.assertNotNull(thing3);
        Assert.assertEquals(thing2, thing3);
        session.delete(thing3);
        tx.commit();
        session.close();
        Assert.assertEquals(LifecycleThing.lifecycleCalls.nextClearBit(0), 7);
    }
}
