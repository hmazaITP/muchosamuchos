package com.itpatagonia.muchosamuchos.controller;

import com.itpatagonia.muchosamuchos.model.State;
import com.itpatagonia.muchosamuchos.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state")
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> getAll() {
        List<State> stateList = (List<State>)stateService.findAll();
        if(stateList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(stateList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@PathVariable("id") Long id) {
        State state = (State)stateService.findById(id).get();
        if(state == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(state);
    }

    @PostMapping()
    public ResponseEntity<State> save(@RequestBody State state) {
        State stateNew = (State)stateService.create(Optional.of(state)).get();
        return ResponseEntity.ok(stateNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody State state) {
        State stateUpdated = (State)stateService.update(id, Optional.of(state)).get();
        return ResponseEntity.ok(stateUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if(stateService.delete(id)){
            return ResponseEntity.ok("Eliminado");
        }else{
            return ResponseEntity.status(400).body("Error al eliminar");
        }

    }
}
