package com.akkulov.controller;

import com.akkulov.common.model.SendMessageDto;
import com.akkulov.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для распределения сообщений.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageDistributor {

  /**
   * Распределить апдейт на нужную очередь.
   *
   * @param update апдейт
   * @return ответ пользователю
   */
  public SendMessageDto processMessage(Update update) {
    if (update.getEditedMessage() != null) {
      return null;
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
  private SendMessageDto processTextMessageType(Update update) {
    return MessageUtils.textMessage(update, update.getMessage().getText());
  }

  /**
   * Обработать неподдерживаемый вид сообщения.
   *
   * @param update апдейт
   */
  private SendMessageDto processUnsupportedMessageType(Update update) {
    log.error("Unsupported message type from user: username={}",
        update.getMessage().getFrom().getUserName()
    );
    return MessageUtils.textMessage(update,
        "Неподдерживаемый вид сообщения! Введите название песни!");
  }
}
