package com.itpatagonia.muchosamuchos.service;

import com.itpatagonia.muchosamuchos.model.State;
import com.itpatagonia.muchosamuchos.repository.StateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class StateService implements IABM{
    @Autowired
    StateRepository stateRepository;
    @Override
    public Optional<?> create(Optional<?> optional) {
        State state = stateRepository.save((State)optional.get());
        return Optional.of(state);
    }

    @Override
    public List<?> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public Optional<?> findById(Long id) {
        return stateRepository.findById(id);
    }

    @Override
    public Optional<?> update(Long id, Optional<?> optional) {
        State stateOld = stateRepository.findById(id).get();
        stateOld.setId(((State)optional.get()).getId());
        stateOld.setName(((State)optional.get()).getName());
        stateOld.setCountryid(((State)optional.get()).getCountryid());
        return Optional.of(stateRepository.save(stateOld));
    }

    @Override
    public Boolean delete(Long id) {
        State state = stateRepository.findById(id).get();
        try {
            stateRepository.delete(state);
            return true;
        }catch (Exception exception){
            log.error("Error al eliminar un state ", exception);
            return false;
        }

    }
}
