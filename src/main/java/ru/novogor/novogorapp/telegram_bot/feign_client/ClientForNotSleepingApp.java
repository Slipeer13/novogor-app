package ru.novogor.novogorapp.telegram_bot.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;

@EnableAsync
@FeignClient(name = "client", url="${url.app}")
public interface ClientForNotSleepingApp {

    @Async
    @GetMapping("/")
    void checkForNotSleeping();
}
