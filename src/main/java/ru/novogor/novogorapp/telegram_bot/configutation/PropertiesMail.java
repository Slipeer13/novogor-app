package ru.novogor.novogorapp.telegram_bot.configutation;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
public record PropertiesMail(String host, String login, String pass) {
}
