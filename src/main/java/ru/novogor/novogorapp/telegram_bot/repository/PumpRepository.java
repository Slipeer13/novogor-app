package ru.novogor.novogorapp.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novogor.novogorapp.telegram_bot.entity.Pump;
import ru.novogor.novogorapp.telegram_bot.entity.Station;

public interface PumpRepository extends JpaRepository<Pump, Long> {

    Pump findPumpByNameAndStationName(String namePump, String nameStation);

}
