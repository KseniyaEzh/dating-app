package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Gender;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.GenderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {
    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<Gender> findAll() {
        return genderRepository.findAll();
    }

    public Gender findOne(int id) {
        Optional<Gender> foundPerson = genderRepository.findById(id);
        return foundPerson.orElse(null);
    }
}
