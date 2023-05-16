package com.akkulov.utils;

import com.akkulov.model.SendMessageDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Утилитный класс для отправки сообщений.
 */
public final class MessageUtils {

  private MessageUtils() {
  }

  /**
   * Собрать ответ с текстом.
   *
   * @param update сообщение
   * @param text   текст сообщения
   */
  public static SendMessage textMessage(Update update, String text) {
    return SendMessage.builder()
        .chatId(update.getMessage().getChatId())
        .text(text)
        .build();
  }
}
