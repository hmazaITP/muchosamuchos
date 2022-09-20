package com.itpatagonia.muchosamuchos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itpatagonia.muchosamuchos.model.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}
