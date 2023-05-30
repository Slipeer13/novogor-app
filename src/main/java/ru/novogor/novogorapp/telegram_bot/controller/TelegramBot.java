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

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private PropertiesBot propertiesBot;

    @Autowired
    IdMembersService idMembersService;

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
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            if (messageText.equals(propertiesBot.pass())) {
                message.setChatId(String.valueOf(chatId));
                message.setText("пароль подтверждён");
                idMembersService.save(new IdMember(chatId));
            } else {
                if (!idMembersService.isPresent(chatId)) {
                    message.setChatId(String.valueOf(chatId));
                    message.setText("введите пароль: ");
                } else {
                    message.setChatId(String.valueOf(chatId));

                    message.setText("продолжаем...");
                }
            }

            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
