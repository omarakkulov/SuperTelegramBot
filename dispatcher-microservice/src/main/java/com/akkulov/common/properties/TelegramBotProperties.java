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
@PropertySource("classpath:bot-config.properties")
@ConfigurationProperties(prefix = "bot")
public class TelegramBotProperties {

  private String username;
  private String token;
}
