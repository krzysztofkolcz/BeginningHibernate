package siia.helloworld.gateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class HelloWorldExample {

  public static void main(String args[]) {
      String cfg = "";
      ApplicationContext context = new ClassPathXmlApplicationContext( "springGatewayConfig.xml");
      HelloService helloService = context.getBean("helloGateway", HelloService.class);
      System.out.println(helloService.sayHello("World"));
  }

}
