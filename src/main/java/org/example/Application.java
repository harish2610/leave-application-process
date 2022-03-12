package org.example;

import org.camunda.bpm.client.spring.SpringTopicSubscription;
import org.camunda.bpm.client.spring.event.SubscriptionInitializedEvent;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication("leaveApplicationProcess")
public class Application {

    protected static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(SubscriptionInitializedEvent.class)
    public void catchSubscriptionInitEvent(SubscriptionInitializedEvent event) {

        SpringTopicSubscription topicSubscription = event.getSource();
        if (!topicSubscription.isAutoOpen()) {

            // open topic in case it is not opened already
            topicSubscription.open();

            LOG.info("Subscription with topic name '{}' has been opened!",
                    topicSubscription.getTopicName());
        }
    }
}
