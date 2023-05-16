package com.akkulov.service.queue.consumer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Инттерфейс для получения сообщений от брокера.
 */
public interface AnswerConsumer {

  /**
   * Получить сообщение.
   *
   * @param sendMessage сообщение
   */
  void consume(SendMessage sendMessage);
}
