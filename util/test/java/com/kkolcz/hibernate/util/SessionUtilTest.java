package com.kkolcz.hibernate.util;


import org.testng.annotations.Test;
public class SessionUtilTest{
  @Test
  public void testSessionFactory() {
    Session session = SessionUtil.getSession();
    session.close();
  }
}
