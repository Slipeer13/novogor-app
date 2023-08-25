package ru.novogor.novogorapp.telegram_bot.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

@EnableAsync
@FeignClient(url="${url.app}")
public interface ClientForNotSleepingApp {

    @Async
    @Scheduled(fixedRate = 20 * 60000)
    @GetMapping("/")
    void checkForNotSleeping();
}
