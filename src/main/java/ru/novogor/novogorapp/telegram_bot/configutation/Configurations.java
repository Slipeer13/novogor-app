package ru.novogor.novogorapp.telegram_bot.configutation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.novogor.novogorapp.telegram_bot.controller.TelegramBot;

@Configuration
@EnableScheduling
public class Configurations {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBot TelegramBot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(TelegramBot);
        return api;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(); // используем простой планировщик задач
    }
}
