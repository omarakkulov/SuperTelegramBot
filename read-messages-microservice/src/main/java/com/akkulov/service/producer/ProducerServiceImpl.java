package com.akkulov.service.producer;


import com.akkulov.common.properties.RabbitQueueProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void produceAnswer(SendMessage sendMessage) {
    rabbitTemplate.convertAndSend("textQueue", sendMessage);
  }
}
