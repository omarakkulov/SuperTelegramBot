package com.akkulov.service.consumer;

import com.akkulov.service.command.TelegramUserMessagesCommand;
import com.akkulov.service.producer.ReadMessagesProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadMessagesConsumerServiceImpl implements ReadMessagesConsumerService {

  private final ReadMessagesProducerService readMessagesProducerService;
  private final TelegramUserMessagesCommand telegramUserMessagesCommand;

  @RabbitListener(queues = "textQueue")
  @Override
  public void consumeTextMessageUpdates(Update update) {
    log.info("readMessagesMicroservice: Incoming message from RabbitMQ");
    var sendMessage = telegramUserMessagesCommand.execute(update);
    if (sendMessage == null) {
      readMessagesProducerService.produceAnswer(
          SendMessage.builder()
              .chatId(update.getMessage().getChatId())
              .text("Не смогли сохранить ваши данные :(")
              .build()
      );
    }

    readMessagesProducerService.produceAnswer(sendMessage);
  }
}
