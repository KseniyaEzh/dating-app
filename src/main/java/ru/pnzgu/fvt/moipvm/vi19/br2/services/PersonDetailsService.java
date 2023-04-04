package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Person;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.PeopleRepository;
import ru.pnzgu.fvt.moipvm.vi19.br2.security.PersonDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public Person update(int id, Person person) {
        Person personInBase = peopleRepository.findById(id).orElse(null);
        if (personInBase != null) {
            person.setPhoto(personInBase.getPhoto());
        }
        person.setId(id);
        peopleRepository.save(person);
        return personInBase;
    }
}
