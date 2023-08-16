package ru.novogor.novogorapp.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novogor.novogorapp.telegram_bot.entity.Station;
import ru.novogor.novogorapp.telegram_bot.repository.StationRepository;

import java.util.List;

@Service
public class StationServiceImpl implements StationService{

    @Autowired
    private StationRepository stationRepository;
    @Override
    public List<Station> getAllStation() {
        return stationRepository.findAll();
    }
}
