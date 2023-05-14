package com.akkulov.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Транспортная сущность для ответа пользователю.
 */
@Getter
@Builder
@ToString
public class SendMessageDto {

  private final Long chatId;
  private final String username;
  private final String text;
}
