package ru.novogor.novogorapp.telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novogor.novogorapp.telegram_bot.entity.IdMember;

public interface IdMembersRepository extends JpaRepository<IdMember, Long> {
}
