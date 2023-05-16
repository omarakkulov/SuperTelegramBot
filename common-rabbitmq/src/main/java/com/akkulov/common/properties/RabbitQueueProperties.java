package com.akkulov.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Пропертя телеграм бота.
 */
@Getter
@Setter
@PropertySource("classpath:rabbitqueue.properties")
@ConfigurationProperties(prefix = "queue")
public class RabbitQueueProperties {

  /**
   * Получить название очереди для обработки текстовых сообщений.
   */
  private String textMessageQueueName;

  /**
   * Получить название очереди, в которую будут помещаться ответы.
   */
  private String answerMessageQueueName;
}
