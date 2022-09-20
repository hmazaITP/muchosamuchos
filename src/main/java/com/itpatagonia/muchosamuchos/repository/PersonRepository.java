package com.itpatagonia.muchosamuchos.repository;

import com.itpatagonia.muchosamuchos.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
