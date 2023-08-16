package ru.novogor.novogorapp.telegram_bot.service;

import ru.novogor.novogorapp.telegram_bot.entity.Pump;

import java.util.List;

public interface PumpService {

    void save(Pump pump);

    void update(Pump pump, Pump newPump);

    List<Pump> getPumpsFromStation(String station);

    List<Pump> getPumpByStatus(String status);
}
