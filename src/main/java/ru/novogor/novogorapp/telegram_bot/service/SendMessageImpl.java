package ru.novogor.novogorapp.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.novogor.novogorapp.telegram_bot.configutation.PropertiesBot;
import ru.novogor.novogorapp.telegram_bot.entity.IdMember;

import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class SendMessageImpl implements SendMessageService{

    @Autowired
    private PropertiesBot propertiesBot;
    @Autowired
    private IdMembersService idMembersService;
    @Autowired
    private PumpService pumpService;
    @Autowired
    private StationService stationService;

    @Override
    public SendMessage getMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageText = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        String result;
        if(idMembersService.isPresent(chatId)) {
            result = getResponseText(messageText);

        } else {
            result = checkNewUser(messageText, chatId);
        }
        sendMessage.setText(result);
        sendMessage.setChatId(chatId);
        return sendMessage;
    }

    @Override
    public String getResponseText(String messageText) {
        String result = null;
        String resultFromRequest;
        messageText = messageText.toLowerCase(Locale.ROOT);
        if (messageText.contains("авар")) {
            resultFromRequest = pumpService.getPumpByStatus("аварийное").stream().map(e-> e + "\n").collect(Collectors.joining());
            result = resultFromRequest.isEmpty() ? "нет аварийных" : resultFromRequest;
        }
        else if (messageText.contains("трев")) {
            resultFromRequest = pumpService.getPumpByStatus("тревожное").stream().map(e-> e + "\n").collect(Collectors.joining());
            result = resultFromRequest.isEmpty() ? "нет тревожных" : resultFromRequest;
        }
        else if (messageText.contains("хор")) {
            resultFromRequest = pumpService.getPumpByStatus("хорошее").stream().map(e-> e + "\n").collect(Collectors.joining());
            result = resultFromRequest.isEmpty() ? "нет хороших" : resultFromRequest;
        }
        else if (messageText.contains("все")) {
            String[] request = messageText.split("все");
            if(request.length > 1) {
                resultFromRequest = pumpService.getPumpsFromStation(request[1].trim()).stream().map(e -> e
                        + " " + e.getNote() + "\n").collect(Collectors.joining());
                result = resultFromRequest.isEmpty() ? "нет станции " + request[1].trim() : resultFromRequest;
            }
        }
        else if (messageText.contains("станции")) {
            resultFromRequest = stationService.getAllStation().stream().map(e -> e + "\n").collect(Collectors.joining());
            result = resultFromRequest.isEmpty() ? "нет станций" : resultFromRequest;
        }
        if(result == null) result = "запрос не понятен, возможные запросы:\nаварийные\nтревожные\nхорошие\nвсе {станция}\nстанции";
            return result;
    }

    @Override
    public String checkNewUser(String text, long id) {
        String result;
        if (text.equals(propertiesBot.pass())) {
            result = "пароль подтверждён";
            idMembersService.save(new IdMember(id));
        } else {
            result = "введите пароль";
        }
        return result;
    }
}
