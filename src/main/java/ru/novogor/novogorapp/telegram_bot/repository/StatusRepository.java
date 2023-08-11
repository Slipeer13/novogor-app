package ru.novogor.novogorapp.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novogor.novogorapp.telegram_bot.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findStatusByName(String name);
}
