package ru.novogor.novogorapp.telegram_bot.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.novogor.novogorapp.telegram_bot.entity.Pump;
import ru.novogor.novogorapp.telegram_bot.entity.Station;
import ru.novogor.novogorapp.telegram_bot.entity.Status;
import ru.novogor.novogorapp.telegram_bot.repository.PumpRepository;
import ru.novogor.novogorapp.telegram_bot.repository.StationRepository;
import ru.novogor.novogorapp.telegram_bot.repository.StatusRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PumpServiceImpl implements PumpService {

    @Autowired
    private PumpRepository pumpRepository;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Pump> getAllPumps(String messageText) {
        return pumpRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Pump pump) {
        Optional<Pump> pumpOptional = Optional.ofNullable(pumpRepository.findPumpByNameAndStationName(pump.getName(), pump.getStation().getName()));
        if(pumpOptional.isPresent()) {
            Pump oldPump = pumpOptional.get();
            if(oldPump.getDateVibroDiagnostic().before(pump.getDateVibroDiagnostic())) {
                update(oldPump, pump);
            }
        } else {
            Optional<Station> stationOptional = Optional.ofNullable(stationRepository.findStationByName(pump.getStation().getName()));
            Optional<Status> statusOptional = Optional.ofNullable(statusRepository.findStatusByName(pump.getStatus().getName()));
            stationOptional.ifPresent(pump::setStation);
            statusOptional.ifPresent(pump::setStatus);
            pumpRepository.save(pump);
        }
    }

    @Override
    public void update(Pump oldPump, Pump newPump) {
        oldPump.setNote(newPump.getNote());
        oldPump.setStatus(newPump.getStatus());
        oldPump.setDateVibroDiagnostic(newPump.getDateVibroDiagnostic());
        pumpRepository.save(oldPump);
    }
}