package com.kkolcz.session;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import com.kkolcz.model.Product;

@Component("sessionCart")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionCart implements Serializable{

  private static final long serialVersionUID = 1204912580698406L;

  private List<Product> items = new ArrayList<Product>();


}
