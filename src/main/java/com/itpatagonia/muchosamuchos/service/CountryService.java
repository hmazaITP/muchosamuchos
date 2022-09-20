package com.itpatagonia.muchosamuchos.service;

import com.itpatagonia.muchosamuchos.model.Country;
import com.itpatagonia.muchosamuchos.repository.CountryRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CountryService implements IABM{
    @Autowired
    CountryRepository countryRepository;
    @Override
    public Optional<?> create(Optional<?> optional) {
        Country country = countryRepository.save((Country)optional.get());
        return Optional.of(country);
    }

    @Override
    public List<?> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<?> findById(Long id) {
        return countryRepository.findById(id.intValue());
    }

    @Override
    public Optional<?> update(Long id, Optional<?> optional) {
        Country countryOld = countryRepository.findById(id.intValue()).get();
        countryOld.setIso(((Country)optional.get()).getIso());
        countryOld.setName(((Country)optional.get()).getIso());
        return Optional.of(countryRepository.save(countryOld));
    }

    @Override
    public Boolean delete(Long id) {
        Country country = countryRepository.findById(id.intValue()).get();
        try {
            countryRepository.delete(country);
            return true;
        }catch (Exception exception){
            log.error("Error al eliminar un country ", exception);
            return false;
        }

    }
}
