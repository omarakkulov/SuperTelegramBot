package com.akkulov.common.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Общий класс с пропертями.
 */
@Import({
    TelegramBotProperties.class,
    RabbitQueueProperties.class
})
@Configuration
public class ConfigurationProperties {

}
