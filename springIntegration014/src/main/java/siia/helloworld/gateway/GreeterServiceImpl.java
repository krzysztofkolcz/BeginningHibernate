package siia.helloworld.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService
{
    @Autowired
    private HelloService helloWorldGateway;

    @Override
    public void greet2(String name)
    {
        System.out.println( helloWorldGateway.getHelloMessage( name ) );
    }
}
