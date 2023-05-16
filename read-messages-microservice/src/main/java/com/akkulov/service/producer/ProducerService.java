package com.akkulov.service.producer;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Сервис для отправки ответа брокеру.
 */
public interface ProducerService {

  /**
   * Отправить сообщение в брокер.
   *
   * @param sendMessage сообщение
   */
  void produceAnswer(SendMessage sendMessage);
}
