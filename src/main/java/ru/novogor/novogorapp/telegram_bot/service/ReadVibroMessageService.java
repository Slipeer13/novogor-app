package ru.novogor.novogorapp.telegram_bot.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

public interface ReadVibroMessageService {

    void readAndSaveVibroMessage(Message message) throws MessagingException, IOException, GeneralSecurityException, InterruptedException, ParseException;
}
