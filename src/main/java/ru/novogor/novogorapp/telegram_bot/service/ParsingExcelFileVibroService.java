package ru.novogor.novogorapp.telegram_bot.service;

import ru.novogor.novogorapp.telegram_bot.entity.Pump;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.text.ParseException;

public interface ParsingExcelFileVibroService {

    Pump getPumpFromExcelFileVibro(MimeBodyPart part) throws MessagingException, IOException, ParseException;
}
