package com.akkulov.service.consumer;

import com.akkulov.service.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

  private final ProducerService producerService;

  @RabbitListener(queues = "textQueue")
  @Override
  public void consumeTextMessageUpdates(Update update) {
    log.info("readMessagesMicroservice: Text message has received");

    var message = update.getMessage();
    var sendMessage = SendMessage.builder()
        .chatId(message.getChatId())
        .text("Hello from readMessagesMicroservice")
        .build();

    producerService.produceAnswer(sendMessage);
  }
}
