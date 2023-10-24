package ru.novogor.novogorapp.telegram_bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novogor.novogorapp.telegram_bot.entity.IdMember;
import ru.novogor.novogorapp.telegram_bot.repository.IdMembersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdMembersServiceImpl implements IdMembersService{

    @Autowired
    private IdMembersRepository idMembersRepository;
    private final List<Long> listId = new ArrayList<>();

    @Override
    public boolean isPresent(long id) {
        boolean result = false;
        if(listId.contains(id)) {
            result = true;
        } else {
            if(idMembersRepository.findById(id).isPresent()) {
                listId.add(id);
                result = true;
            }
        }
        return result;
    }

    @Override
    public void save(IdMember id) {
        idMembersRepository.save(id);
    }

}
