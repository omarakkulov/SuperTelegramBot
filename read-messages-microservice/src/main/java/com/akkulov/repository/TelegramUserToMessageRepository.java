package com.akkulov.repository;

import com.akkulov.entity.TelegramUserToMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserToMessageRepository extends JpaRepository<TelegramUserToMessageEntity, Long> {

}
