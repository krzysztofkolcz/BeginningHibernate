package siia.helloworld.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService
{

    @Autowired
    private MessageChannel names;

    @Override
    public void greet(String name)
    {
        names.send( MessageBuilder.withPayload( name ).build() );
    }
}
