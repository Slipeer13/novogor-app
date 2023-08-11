package ru.novogor.novogorapp.telegram_bot.service;

import ru.novogor.novogorapp.telegram_bot.entity.Pump;

import java.util.List;

public interface PumpService {

    List<Pump> getAllPumps(String messageText);

    void save(Pump pump);

    void update(Pump pump, Pump newPump);
}
