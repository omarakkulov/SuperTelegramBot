package com.akkulov.service;

import com.akkulov.entity.TelegramMessageEntity;
import com.akkulov.repository.TelegramMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TelegramMessageService {

  private final TelegramMessageRepository telegramMessageRepository;

  /**
   * Сохранить сообщение от пользователя.
   *
   * @param telegramMessageEntity пользователь и его сообщение
   */
  @Transactional
  public TelegramMessageEntity saveTelegramMessage(TelegramMessageEntity telegramMessageEntity) {
    return telegramMessageRepository.save(telegramMessageEntity);
  }
}
