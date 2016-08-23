package com.kkolcz.session;

import Component;

@Component("sessionCart")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionCart implements Serializable{

  private static final long serialVersionUID = 1204912580698406L;

  private List<Product> items = new ArrayList<Product>();


}
