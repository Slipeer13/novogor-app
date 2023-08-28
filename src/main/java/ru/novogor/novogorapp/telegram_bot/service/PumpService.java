package ru.novogor.novogorapp.telegram_bot.service;

import ru.novogor.novogorapp.telegram_bot.entity.Pump;

import java.util.List;

public interface PumpService {

    void save(Pump pump);

    void update(Pump oldPump, Pump pump);

    List<Pump> getPumpsFromStation(String station);

    List<Pump> getPumpByStatus(String status);
}
