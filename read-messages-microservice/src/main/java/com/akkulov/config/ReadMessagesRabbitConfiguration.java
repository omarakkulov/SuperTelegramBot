package com.akkulov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация очередей и вспомогательных бинов для rabbitmq.
 */
@Configuration
@RequiredArgsConstructor
public class ReadMessagesRabbitConfiguration {

  /**
   * Преобразует приходящий объект апдейта из телеги в json и передает их в rabbitmq.
   * И наоборот, при получении обратно этих json из брокера, преобразует их в java-объект.
   */
  @Bean
  public MessageConverter readMessagesJsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
