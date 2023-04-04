package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Marital;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.MaritalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MaritalService {
    private final MaritalRepository maritalRepository;

    public MaritalService(MaritalRepository maritalRepository) {
        this.maritalRepository = maritalRepository;
    }

    public List<Marital> findAll() {
        return maritalRepository.findAll();
    }

    public Marital findOne(int id) {
        Optional<Marital> foundPerson = maritalRepository.findById(id);
        return foundPerson.orElse(null);
    }
}
