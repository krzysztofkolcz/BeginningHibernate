package chapter04.model;

import chapter04.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleObjectRefreshTest {

    @Test
    public void testRefresh() {
        Long id;
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setKey("testMerge");
        simpleObject.setValue(1L);
        session.save(simpleObject);
        id = simpleObject.getId();
        tx.commit();
        session.close();
        SimpleObject so = validateSimpleObject(id, 1L);
        so.setValue(2L);
        session = SessionUtil.getSession();
        tx = session.beginTransaction();
        session.refresh(so);
        tx.commit();
        session.close();
        validateSimpleObject(id, 1L);
    }
    private SimpleObject validateSimpleObject(Long id, Long value) {
        Session session;
        Transaction tx;// validate the database values
        session = SessionUtil.getSession();
        tx = session.beginTransaction();
        SimpleObject so = (SimpleObject) session.load(SimpleObject.class, id);
        assertEquals(so.getKey(), "testMerge");
        assertEquals(so.getValue(), value);
        tx.commit();
        session.close();
        return so;
    }
}
