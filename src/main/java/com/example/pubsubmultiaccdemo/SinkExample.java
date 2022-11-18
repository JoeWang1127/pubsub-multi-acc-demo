package com.example.pubsubmultiaccdemo;

import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class SinkExample {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Bean
  public Consumer<Message<UserMessage>> logUserMessage() {
    return message -> {
      UserMessage userMessage = message.getPayload();
      BasicAcknowledgeablePubsubMessage ack =
          GcpPubSubHeaders.getOriginalMessage(message)
              .orElseThrow(
                  () -> new IllegalStateException("Could not find original PubSubMessage."));
      log.info(
          "Receive message from {}: {}",
          userMessage.getUsername(),
          userMessage.getBody());
      ack.ack();
    };
  }
}
