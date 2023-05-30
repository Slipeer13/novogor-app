package ru.novogor.novogorapp.telegram_bot.configutation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot")
public record PropertiesBot(String token, String username, String pass) {
}
