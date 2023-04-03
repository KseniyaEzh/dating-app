package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Gender;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.GenderRepository;

import java.util.List;

@Service
public class GenderService {
    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<Gender> findAll() {
        return genderRepository.findAll();
    }
}
