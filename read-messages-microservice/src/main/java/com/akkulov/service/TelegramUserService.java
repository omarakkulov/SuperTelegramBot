package com.akkulov.service;

import com.akkulov.entity.TelegramUserEntity;
import com.akkulov.repository.TelegramUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TelegramUserService {

  private final TelegramUserRepository telegramUserRepository;

  /**
   * Сохранить пользователя.
   *
   * @param user пользователь
   */
  @Transactional
  public TelegramUserEntity saveUser(TelegramUserEntity user) {
    var telegramUserOpt = telegramUserRepository.findByChatId(user.getChatId());
    if (telegramUserOpt.isEmpty()) {
      return telegramUserRepository.save(user);
    }

    return telegramUserOpt.get();
  }
}
