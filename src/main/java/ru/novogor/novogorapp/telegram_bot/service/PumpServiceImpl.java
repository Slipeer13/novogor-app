package ru.novogor.novogorapp.telegram_bot.service;

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
    @Transactional
    public void save(Pump pump) {
        Optional<Pump> pumpOptional = Optional.ofNullable(pumpRepository.findPumpByNameAndStationName(pump.getName(), pump.getStation().getName()));
        if(pumpOptional.isPresent()) {
            Pump oldPump = pumpOptional.get();
            if(oldPump.getDateVibroDiagnostic().before(pump.getDateVibroDiagnostic())
                    || oldPump.getDateVibroDiagnostic() == pump.getDateVibroDiagnostic()) {
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
    @Transactional
    public void update(Pump oldPump, Pump pump) {
        oldPump.setNote(pump.getNote());
        Optional<Status> statusOptional = Optional.ofNullable(statusRepository.findStatusByName(pump.getStatus().getName()));
        statusOptional.ifPresent(pump::setStatus);
        oldPump.setDateVibroDiagnostic(pump.getDateVibroDiagnostic());
        pumpRepository.save(oldPump);
    }

    @Override
    @Transactional
    public List<Pump> getPumpsFrom(String station) {
        List<Station> stations = stationRepository.findAll();
        List<Station> stationList = stations.stream().filter(s -> s.getName().contains(station)).toList();
        return stationList.stream().flatMap(s -> s.getPumpList().stream()).toList();
    }

    @Override
    public List<Pump> getPumpByStatus(String status) {
        return pumpRepository.findPumpByStatusName(status);
    }
}