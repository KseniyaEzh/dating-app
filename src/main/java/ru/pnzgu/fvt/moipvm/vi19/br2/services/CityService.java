package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.City;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.CityRepository;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
