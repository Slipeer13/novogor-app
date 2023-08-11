package ru.novogor.novogorapp.telegram_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.novogor.novogorapp.telegram_bot.configutation.PropertiesBot;
import ru.novogor.novogorapp.telegram_bot.entity.IdMember;
import ru.novogor.novogorapp.telegram_bot.service.IdMembersService;
import ru.novogor.novogorapp.telegram_bot.service.SendMessageService;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private PropertiesBot propertiesBot;

    @Autowired
    SendMessageService sendMessageService;

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
            SendMessage message = sendMessageService.getMessage(update);
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
