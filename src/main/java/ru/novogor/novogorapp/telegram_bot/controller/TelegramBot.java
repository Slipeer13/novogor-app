package ru.novogor.novogorapp.telegram_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.novogor.novogorapp.telegram_bot.configutation.PropertiesBot;
import ru.novogor.novogorapp.telegram_bot.service.SendMessageService;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private PropertiesBot propertiesBot;

    @Autowired
    private SendMessageService sendMessageService;

    public TelegramBot(@Value("${bot.token}") String token) {
        super(token);
    }

    @Override
    public String getBotUsername() {
        return propertiesBot.username();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendMessageService.getMessage(update).forEach(m-> {
                try {
                    execute(m);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
