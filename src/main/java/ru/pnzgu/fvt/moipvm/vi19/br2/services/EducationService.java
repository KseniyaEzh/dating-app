package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Education;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.EducationRepository;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public List<Education> findAll() {
        return educationRepository.findAll();
    }
}
