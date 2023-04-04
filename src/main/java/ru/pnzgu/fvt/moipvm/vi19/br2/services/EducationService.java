package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Education;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.EducationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    public Education findOne(int id) {
        Optional<Education> foundPerson = educationRepository.findById(id);
        return foundPerson.orElse(null);
    }
}
