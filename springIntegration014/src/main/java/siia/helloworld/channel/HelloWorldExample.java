package siia.helloworld.channel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class HelloWorldExample {

  public static void main(String args[]) {
      String cfg = "";
      ApplicationContext context = new ClassPathXmlApplicationContext( "springConfig.xml");
      MessageChannel channel = context.getBean("names", MessageChannel.class);
      Message<String> message = MessageBuilder.withPayload("World").build();
      channel.send(message);
  }

}
