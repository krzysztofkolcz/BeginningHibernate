package simy.sample;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.lang.Thread;

public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        template.convertAndSend("Hello, world!");
        try{
          Thread.sleep(1000);
        }catch(InterruptedException e){

        }
        ctx.destroy();
    }
}
