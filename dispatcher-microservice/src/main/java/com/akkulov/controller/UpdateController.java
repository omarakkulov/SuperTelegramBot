package com.akkulov.controller;

import com.akkulov.model.SendMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Сервис, куда приходит сообщение, которое дальше отправляется на распределитель сообщений.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateController {

  private TelegramBot telegramBot;
  private final MessageDistributor messageDistributor;

  /**
   * Заинжектить ссылку телеграм-бота в распределитель сообщений.
   *
   * @param telegramBot ссылка на объект телеграм-бота
   */
  public void registerBot(TelegramBot telegramBot) {
    this.telegramBot = telegramBot;
  }

  /**
   * Обработать входящее сообщение.
   *
   * @param update входящее сообщение (апдейт)
   */
  public void processMessage(Update update) {
    var sendMessageOpt = messageDistributor.processMessage(update);
    // если сообщение просто отредачили, то ничего не делать
    if (sendMessageOpt.isEmpty()) {
      return;
    }
    var sendMessage = sendMessageOpt.get();

    sendResponseToUser(sendMessage);
  }

  /**
   * Отправить ответ пользователю.
   *
   * @param sendMessage ответ пользователю
   */
  public void sendResponseToUser(SendMessage sendMessage) {
    telegramBot.sendResponse(sendMessage);
  }
}
