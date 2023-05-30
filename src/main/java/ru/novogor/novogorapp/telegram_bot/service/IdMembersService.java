package ru.novogor.novogorapp.telegram_bot.service;

import ru.novogor.novogorapp.telegram_bot.entity.IdMember;

public interface IdMembersService {

    boolean isPresent(long id);

    void  save(IdMember id);

}
