package siia.helloworld.channel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class HelloWorldExample {

  public static void main(String args[]) {
    ApplicationContext context = new ClassPathXmlApplicationContext( "springChannelConfig.xml");
    /* HelloService helloService = context.getBean("helloGateway", HelloService.class); */
    /* System.out.println(helloService.sayHello("World")); */
    GreeterService greeterService = context.getBean( "greeterServiceImpl", GreeterService.class );
    greeterService.greet( "Spring Integration!" );
  }

}
