package com.akkulov.service.consumer;

import com.akkulov.model.UserEntity;
import com.akkulov.service.UserService;
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
public class ReadMessagesConsumerServiceImpl implements ConsumerService {

  private final ProducerService producerService;
  private final UserService userService;

  @RabbitListener(queues = "textQueue")
  @Override
  public void consumeTextMessageUpdates(Update update) {
    log.info("readMessagesMicroservice: Incoming message from RabbitMQ");

    UserEntity tmpUser = UserEntity.builder()
        .username(update.getMessage().getFrom().getUserName())
        .chatId(update.getMessage().getChatId())
        .chatText(update.getMessage().getText())
        .build();

    var savedUser = userService.saveUser(tmpUser);
    if (savedUser == null) {
      producerService.produceAnswer(
          SendMessage.builder()
              .chatId(update.getMessage().getChatId())
              .text("Не смогли сохранить ваши данные :(")
              .build()
      );
      return;
    }

    String msg = String.format("Ваши данные сохранены, пользователь=%s", savedUser.getUsername());
    var sendMessage = SendMessage.builder()
        .chatId(update.getMessage().getChatId())
        .text(msg)
        .build();

    producerService.produceAnswer(sendMessage);
  }
}
