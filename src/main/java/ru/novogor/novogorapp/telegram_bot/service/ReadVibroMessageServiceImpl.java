package ru.novogor.novogorapp.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novogor.novogorapp.telegram_bot.entity.Pump;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.text.ParseException;

@Service
public class ReadVibroMessageServiceImpl implements ReadVibroMessageService {
    @Autowired
    private ParsingExcelFileVibroService parsingExcelFileVibroService;
    @Autowired
    private PumpService pumpService;

    @Override
    public void readAndSaveVibroMessage(Message message) throws MessagingException, IOException, ParseException {
        Object content = message.getContent();
        if (content instanceof Multipart multipart) {
            for (int i = 0; i < multipart.getCount(); i++) {
                MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(i);
                Pump pump = parsingExcelFileVibroService.getPumpFromExcelFileVibro(part);
                if(pump != null) pumpService.save(pump);
            }
        }
    }
}
