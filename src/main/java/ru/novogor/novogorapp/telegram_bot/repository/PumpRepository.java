package ru.novogor.novogorapp.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novogor.novogorapp.telegram_bot.entity.Pump;

import java.util.List;

public interface PumpRepository extends JpaRepository<Pump, Long> {

    Pump findPumpByNameAndStationName(String namePump, String nameStation);

    List<Pump> findPumpByStatusName(String status);

    List<Pump> findPumpByStationName(String station);
}
