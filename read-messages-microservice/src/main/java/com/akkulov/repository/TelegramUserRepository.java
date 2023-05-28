package com.akkulov.repository;

import com.akkulov.entity.TelegramUserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUserEntity, Long> {

  Optional<TelegramUserEntity> findByChatId(Long chatId);
}
