package com.akkulov.controller;

import com.akkulov.common.properties.TelegramBotProperties;
import com.akkulov.model.SendMessageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс телеграм-бота.
 */
@Slf4j
@Getter
@Setter
@Component
public class TelegramBot extends TelegramLongPollingBot {

  private final TelegramBotProperties telegramBotProperties;
  private final UpdateController updateController;

  /**
   * TelegramBot.
   *
   * @param telegramBotProperties TelegramBotProperties
   * @param updateController      UpdateController
   */
  @Autowired
  public TelegramBot(
      TelegramBotProperties telegramBotProperties,
      UpdateController updateController) {
    this.telegramBotProperties = telegramBotProperties;
    this.updateController = updateController;
    this.updateController.registerBot(this);
  }

  /**
   * Получить сообщение от пользователя.
   *
   * @param update входящее сообщение (апдейт)
   */
  @Override
  public void onUpdateReceived(Update update) {
    if (update.getMessage() != null) {
      log.info("Incoming message: chatId={}, username={}, message={}",
          update.getMessage().getChatId(),
          update.getMessage().getFrom().getUserName(),
          update.getMessage().getText()
      );
    }

    updateController.processMessage(update);
  }

  /**
   * Отправить ответ пользователю.
   *
   * @param sendMessageDto ответ пользователю
   */
  public void sendResponse(SendMessageDto sendMessageDto) {
    var sendMessage = SendMessage.builder()
        .chatId(sendMessageDto.getChatId())
        .text(sendMessageDto.getText())
        .build();

    try {
      execute(sendMessage);
      log.info("Success send response to user: text={}, chatId={}, username={}",
          sendMessageDto.getText(),
          sendMessageDto.getChatId(),
          sendMessageDto.getUsername()
      );
    } catch (TelegramApiException e) {
      log.warn("Error while during sending message to user: text={}, chatId={}, username={}",
          sendMessageDto.getText(),
          sendMessageDto.getChatId(),
          sendMessageDto.getUsername());
      throw new IllegalStateException(e);
    }
  }

  public void sendResponse(SendMessage sendMessage) {
    try {
      execute(sendMessage);
      log.info("Success send response to user: text={}, chatId={}",
          sendMessage.getText(),
          sendMessage.getChatId()
      );
    } catch (TelegramApiException e) {
      log.warn("Error while during sending message to user: text={}, chatId={}",
          sendMessage.getText(),
          sendMessage.getChatId());
      throw new IllegalStateException(e);
    }
  }

  @Override
  public String getBotUsername() {
    return telegramBotProperties.getUsername();
  }

  @Override
  public String getBotToken() {
    return telegramBotProperties.getToken();
  }
}
