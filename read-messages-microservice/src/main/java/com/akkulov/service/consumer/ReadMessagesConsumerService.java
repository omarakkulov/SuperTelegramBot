package com.akkulov.service.consumer;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс для получения ответов от брокера.
 */
public interface ReadMessagesConsumerService {

  /**
   * Принять ответ от брокера в наш сервис.
   *
   * @param update ответ
   */
  void consumeTextMessageUpdates(Update update);
}
