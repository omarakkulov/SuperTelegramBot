package com.akkulov.service.command;

import com.akkulov.entity.TelegramMessageEntity;
import com.akkulov.entity.TelegramUserEntity;
import com.akkulov.entity.TelegramUserToMessageEntity;
import com.akkulov.service.TelegramMessageService;
import com.akkulov.service.TelegramUserService;
import com.akkulov.service.TelegramUserToMessageService;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TelegramUserMessagesCommand implements Command<Update, SendMessage> {

//  private final SessionFactory sessionFactory;

  private final TelegramUserService telegramUserService;
  private final TelegramMessageService telegramMessageService;
  private final TelegramUserToMessageService telegramUserToMessageService;

  @Override
  @Transactional(rollbackFor = SQLException.class)
  public SendMessage execute(Update update) {

//    Session session = sessionFactory.openSession();
    TelegramUserEntity tmpUser = TelegramUserEntity.builder()
        .chatId(update.getMessage().getChatId())
        .username(update.getMessage().getFrom().getUserName())
        .build();

    var savedUser = telegramUserService.saveUser(tmpUser);
    if (savedUser == null) {
      return null;
    }

    var telegramMessageData = update.getMessage().getText();
    TelegramMessageEntity telegramMessage = TelegramMessageEntity
        .builder()
        .data(telegramMessageData)
        .username(savedUser.getUsername())
        .build();
    var savedMessage = telegramMessageService.saveTelegramMessage(telegramMessage);
    if (savedMessage == null) {
      return null;
    }

    TelegramUserToMessageEntity telegramUserToMessageEntity = TelegramUserToMessageEntity.builder()
        .telegramUserId(savedUser.getId())
        .telegramMessageId(savedMessage.getId())
        .build();
    var savedUserToMessage = telegramUserToMessageService.saveMessageAndUserRelationship(telegramUserToMessageEntity);
    if (savedUserToMessage == null) {
      return null;
    }

    String msg = String.format("Ваши данные сохранены, пользователь=%s", savedUser.getUsername());

    return SendMessage.builder()
        .chatId(savedUser.getChatId())
        .text(msg)
        .build();
  }
}
