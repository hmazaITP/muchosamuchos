package com.itpatagonia.muchosamuchos.controller;

import com.itpatagonia.muchosamuchos.model.City;
import com.itpatagonia.muchosamuchos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        List<City> cityList = (List<City>)cityService.findAll();
        if(cityList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@PathVariable("id") Long id) {
        City city = (City)cityService.findById(id).get();
        if(city == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(city);
    }

    @PostMapping()
    public ResponseEntity<City> save(@RequestBody City city) {
        City cityNew = (City)cityService.create(Optional.of(city)).get();
        return ResponseEntity.ok(cityNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody City city) {
        City cityUpdated = (City)cityService.update(id, Optional.of(city)).get();
        return ResponseEntity.ok(cityUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if(cityService.delete(id)){
            return ResponseEntity.ok("Eliminado");
        }else{
            return ResponseEntity.status(400).body("Error al eliminar");
        }

    }
}
