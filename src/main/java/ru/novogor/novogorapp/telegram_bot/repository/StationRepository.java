package ru.novogor.novogorapp.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novogor.novogorapp.telegram_bot.entity.Station;

public interface StationRepository extends JpaRepository<Station, Long> {

    Station findStationByName(String name);
}
