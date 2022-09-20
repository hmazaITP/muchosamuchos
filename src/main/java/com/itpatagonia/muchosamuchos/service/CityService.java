package com.itpatagonia.muchosamuchos.service;

import com.itpatagonia.muchosamuchos.model.City;
import com.itpatagonia.muchosamuchos.repository.CityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CityService implements IABM{
    @Autowired
    CityRepository cityRepository;
    @Override
    public Optional<?> create(Optional<?> optional) {
        City city = cityRepository.save((City)optional.get());
        return Optional.of(city);
    }

    @Override
    public List<?> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<?> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public Optional<?> update(Long id, Optional<?> optional) {
        City cityOld = cityRepository.findById(id).get();
        cityOld.setId(((City)optional.get()).getId());
        cityOld.setName(((City)optional.get()).getName());
        cityOld.setState(((City)optional.get()).getState());
        return Optional.of(cityRepository.save(cityOld));
    }

    @Override
    public Boolean delete(Long id) {
        City city = cityRepository.findById(id).get();
        try {
            cityRepository.delete(city);
            return true;
        }catch (Exception exception){
            log.error("Error al eliminar un city ", exception);
            return false;
        }

    }
}
