package com.itpatagonia.muchosamuchos.repository;

import com.itpatagonia.muchosamuchos.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
