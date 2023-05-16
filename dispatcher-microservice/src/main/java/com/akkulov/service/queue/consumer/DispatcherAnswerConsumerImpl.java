package com.akkulov.service.queue.consumer;

import com.akkulov.controller.UpdateController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatcherAnswerConsumerImpl implements AnswerConsumer {

  private final UpdateController updateController;

  @RabbitListener(queues = "textQueue")
  @Override
  public void consume(SendMessage sendMessage) {
    updateController.sendResponseToUser(sendMessage);
  }
}
