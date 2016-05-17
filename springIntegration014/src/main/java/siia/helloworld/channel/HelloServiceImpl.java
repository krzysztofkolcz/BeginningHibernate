package siia.helloworld.channel;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

  @Override
  public void sayHello(String name) {
      System.out.println("Hello " + name);
  }

}
