package com.akkulov.controller;

import com.akkulov.common.properties.RabbitQueueProperties;
import com.akkulov.model.SendMessageDto;
import com.akkulov.service.queue.producer.DispatcherUpdateProducerImpl;
import com.akkulov.utils.MessageUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для распределения сообщений.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageDistributor {

  private final RabbitQueueProperties rabbitQueueProperties;
  private final DispatcherUpdateProducerImpl dispatcherUpdateProducerImpl;

  /**
   * Распределить апдейт на нужную очередь.
   *
   * @param update апдейт
   * @return ответ пользователю
   */
  public Optional<SendMessage> processMessage(Update update) {
    // если сообщение просто отредачили, то ничего не делать
    if (update.getEditedMessage() != null) {
      return Optional.empty();
    }

    if (update.getMessage().getText() == null) {
      return processUnsupportedMessageType(update);
    }

    return processTextMessageType(update);
  }

  /**
   * Обработать текстовое сообщение.
   *
   * @param update апдейт
   */
  private Optional<SendMessage> processTextMessageType(Update update) {
    dispatcherUpdateProducerImpl.produce(rabbitQueueProperties.getTextMessageQueueName(), update);

    return Optional.ofNullable(MessageUtils.textMessage(update, "Сохраняем информацию..."));
  }

  /**
   * Обработать неподдерживаемый вид сообщения.
   *
   * @param update апдейт
   */
  private Optional<SendMessage> processUnsupportedMessageType(Update update) {
    log.error("Unsupported message type from user: username={}",
        update.getMessage().getFrom().getUserName()
    );
    return Optional.ofNullable(MessageUtils.textMessage(update,
        "Неподдерживаемый вид сообщения! Текст!"));
  }
}
