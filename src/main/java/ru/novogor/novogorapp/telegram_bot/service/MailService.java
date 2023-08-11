package ru.novogor.novogorapp.telegram_bot.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

public interface MailService {
    void getMessages() throws MessagingException, IOException, GeneralSecurityException, InterruptedException, ParseException;
}
