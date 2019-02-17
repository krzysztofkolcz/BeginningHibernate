package chapter04.model;

import chapter04.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

public class SimpleObjectTest {

    @Test
    public void testSavingEntitiesTwice() {
        Long id;
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        SimpleObject obj = new SimpleObject();
        obj.setKey("osas");
        obj.setValue(10L);
        session.save(obj);
        assertNotNull(obj.getId());
        id = obj.getId();
        tx.commit();
        session.close();
        session = SessionUtil.getSession();
        tx = session.beginTransaction();
        obj.setValue(12L);
        session.save(obj); // note that save() creates a new row in the database!
        // this is wrong behavior. Don't do this! - Should use saveOrUpdate!
        tx.commit();
        session.close();
        assertNotEquals(id, obj.getId());
    }
}
