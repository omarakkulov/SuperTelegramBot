package com.akkulov.service;

import com.akkulov.entity.TelegramUserToMessageEntity;
import com.akkulov.repository.TelegramUserToMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramUserToMessageService {

  private final TelegramUserToMessageRepository telegramUserToMessageRepository;

  /**
   * Сохранить связь между пользователем и его сообщением.
   *
   * @param telegramUserToMessageEntity связь между пользователем и его сообщением
   */
  public TelegramUserToMessageEntity saveMessageAndUserRelationship(TelegramUserToMessageEntity telegramUserToMessageEntity) {
    return telegramUserToMessageRepository.save(telegramUserToMessageEntity);
  }
}
