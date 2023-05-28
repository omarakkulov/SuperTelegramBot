package com.akkulov.repository;

import com.akkulov.entity.TelegramMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramMessageRepository extends JpaRepository<TelegramMessageEntity, Long> {

}
