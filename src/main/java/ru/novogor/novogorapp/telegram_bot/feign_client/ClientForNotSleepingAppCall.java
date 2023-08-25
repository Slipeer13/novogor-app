package ru.novogor.novogorapp.telegram_bot.feign_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class ClientForNotSleepingAppCall {

    @Autowired
    private ClientForNotSleepingApp client;

    @Async
    @Scheduled(fixedRate = 20 * 60000)
    public void call() {
        client.checkForNotSleeping();
    }
}
