package br.com.antonio.sqsaws.producer;

import br.com.antonio.sqsaws.model.Event;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.antonio.sqsaws.config.AWSConfigConstants.ORDER_CREATED_TOPIC;

@Slf4j
@Service
public class SimpleMessageProducer {

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    public void publish(Event event) {
        notificationMessagingTemplate.convertAndSend(ORDER_CREATED_TOPIC, event);
    }
}
