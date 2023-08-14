package ru.novogor.novogorapp.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novogor.novogorapp.telegram_bot.entity.IdMember;
import ru.novogor.novogorapp.telegram_bot.repository.IdMembersRepository;

@Service
public class IdMembersServiceImpl implements IdMembersService{

    @Autowired
    private IdMembersRepository idMembersRepository;

    @Override
    public boolean isPresent(long id) {
        return idMembersRepository.findById(id).isPresent();
    }

    @Override
    public void save(IdMember id) {
        idMembersRepository.save(id);
    }

}
