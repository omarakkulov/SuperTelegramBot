package com.akkulov.service.queue.producer;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс для доставки сообщений в очередь брокера.
 */
public interface DispatcherUpdateProducer {

  /**
   * Отправить сообщение в очередь.
   *
   * @param queueName название очереди
   * @param update    сообщение
   */
  void produce(String queueName, Update update);
}
