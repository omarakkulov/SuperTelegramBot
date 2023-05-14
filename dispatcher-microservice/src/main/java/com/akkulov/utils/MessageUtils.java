package com.akkulov.utils;

import com.akkulov.common.model.SendMessageDto;
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
  public static SendMessageDto textMessage(Update update, String text) {
    return SendMessageDto.builder()
        .username(update.getMessage().getFrom().getUserName())
        .chatId(update.getMessage().getChatId())
        .text(text)
        .build();
  }
}
