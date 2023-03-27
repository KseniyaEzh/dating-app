package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.PersonInfo;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.PeopleInfoRepository;
import ru.pnzgu.fvt.moipvm.vi19.br2.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
public class PersonInfoService {

    private final PeopleInfoRepository peopleInfoRepository;

    @Autowired
    public PersonInfoService(PeopleInfoRepository peopleInfoRepository) {
        this.peopleInfoRepository = peopleInfoRepository;
    }

    public List<PersonInfo> findAll() {
        return peopleInfoRepository.findAll();
    }

    @Transactional
    public void save(PersonInfo personInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        personInfo.setUser_id(personDetails.getPerson().getId());
        peopleInfoRepository.save(personInfo);
    }








//    public List<PersonInfo> findAll() {
//        return peopleInfoRepository.findAll();
//    }
//
//    public PersonInfo findOne(int id) {
//        Optional<PersonInfo> foundPerson = peopleInfoRepository.findById(id);
//        return foundPerson.orElse(null);
//    }
//
//    @Transactional
//    public void save(PersonInfo personInfo) {
//        peopleInfoRepository.save(personInfo);
//    }
//
//    @Transactional
//    public void update(int id, PersonInfo updatedPerson) {
//        updatedPerson.setId(id);
//        peopleInfoRepository.save(updatedPerson);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        peopleInfoRepository.deleteById(id);
//    }

}
