package ru.novogor.novogorapp.telegram_bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface SendMessageService {

    List<SendMessage> getMessage(Update update);

    String checkNewUser(String text, long id);

    String getResponseText(String messageText);
}
