package com.akkulov.service.queue.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatcherDispatcherUpdateProducerImpl implements DispatcherUpdateProducer {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void produce(String queueName, Update update) {
    log.info("Send message to RabbitMQ: queueName={}", queueName);
    rabbitTemplate.convertAndSend(queueName, update);
  }
}
