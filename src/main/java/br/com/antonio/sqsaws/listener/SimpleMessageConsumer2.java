package br.com.antonio.sqsaws.listener;

import br.com.antonio.sqsaws.model.Event;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import static br.com.antonio.sqsaws.config.AWSConfigConstants.ORDER_QUEUE_2;

@Slf4j
@Controller
public class SimpleMessageConsumer2 implements MessageConsumer {

    @Override
    @SqsListener(value = ORDER_QUEUE_2, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consume(@NotificationMessage Event event) {
        if (event != null) {
            log.info("Received order event for consumer 2: " + event);
        }
    }
}
