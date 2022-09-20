package com.itpatagonia.muchosamuchos.controller;

import com.itpatagonia.muchosamuchos.model.Person;
import com.itpatagonia.muchosamuchos.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> personList = (List<Person>)personService.findAll();
        if(personList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") Long id) {
        Person person = (Person)personService.findById(id).get();
        if(person == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(person);
    }

    @PostMapping()
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person personNew = (Person)personService.create(Optional.of(person)).get();
        return ResponseEntity.ok(personNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Person person) {
        Person personUpdated = (Person)personService.update(id, Optional.of(person)).get();
        return ResponseEntity.ok(personUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if(personService.delete(id)){
            return ResponseEntity.ok("Eliminado");
        }else{
            return ResponseEntity.status(400).body("Error al eliminar");
        }

    }
}
