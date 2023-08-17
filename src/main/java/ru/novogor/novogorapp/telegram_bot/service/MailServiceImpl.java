package ru.novogor.novogorapp.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.novogor.novogorapp.telegram_bot.configutation.PropertiesMail;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private ReadVibroMessageService readVibroMessageService;
    @Autowired
    private PropertiesMail propertiesMail;

    private Store store;

    {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            /*MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);*/
            props.put("mail.imaps.ssl.trust", "*");
            //props.put("mail.imaps.ssl.socketFactory", sf);
            props.put("mail.imaps.ssl.protocols", "TLSv1.2");
            store = Session.getInstance(props).getStore();
        }  catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void getMessages() throws MessagingException, GeneralSecurityException, IOException, InterruptedException, ParseException {
        store.connect(propertiesMail.host(), propertiesMail.login(), propertiesMail.pass());
        // Получение папки с сообщениями
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        Optional<Message[]> messages = Optional.ofNullable(folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)));
        if(messages.isPresent()) {
            for (Message message : messages.get()) {
                if (message.getSubject().contains("вибро")) {
                    readVibroMessageService.readAndSaveVibroMessage(message);
                }
            }
        }
        folder.close(true);
        store.close();
    }
}
