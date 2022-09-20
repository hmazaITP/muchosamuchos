package com.itpatagonia.muchosamuchos.service;

import com.itpatagonia.muchosamuchos.model.Person;
import com.itpatagonia.muchosamuchos.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PersonService implements IABM{
    @Autowired
    PersonRepository personRepository;
    @Override
    public Optional<?> create(Optional<?> optional) {
        Person person = personRepository.save((Person)optional.get());
        return Optional.of(person);
    }

    @Override
    public List<?> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<?> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<?> update(Long id, Optional<?> optional) {
        Person personOld = personRepository.findById(id).get();
        personOld.setId(id);
        personOld.setName(((Person)optional.get()).getName());
        personOld.setSurename(((Person)optional.get()).getSurename());
        personOld.setAddress(((Person)optional.get()).getAddress());
        personOld.setDnu(((Person)optional.get()).getDnu());
        personOld.setCityid(((Person)optional.get()).getCityid());
        personOld.setCourses(((Person)optional.get()).getCourses());
        return Optional.of(personRepository.save(personOld));
    }

    @Override
    public Boolean delete(Long id) {
        Person person = personRepository.findById(id).get();
        try {
            personRepository.delete(person);
            return true;
        }catch (Exception exception){
            log.error("Error al eliminar un person ", exception);
            return false;
        }

    }
}
