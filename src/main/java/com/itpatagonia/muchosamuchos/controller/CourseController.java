package com.itpatagonia.muchosamuchos.controller;

import com.itpatagonia.muchosamuchos.model.Course;
import com.itpatagonia.muchosamuchos.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courseList = (List<Course>)courseService.findAll();
        if(courseList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable("id") Long id) {
        Course course = (Course)courseService.findById(id).get();
        if(course == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(course);
    }

    @PostMapping()
    public ResponseEntity<Course> save(@RequestBody Course course) {
        Course courseNew = (Course)courseService.create(Optional.of(course)).get();
        return ResponseEntity.ok(courseNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Course course) {
        Course courseUpdated = (Course)courseService.update(id, Optional.of(course)).get();
        return ResponseEntity.ok(courseUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if(courseService.delete(id)){
            return ResponseEntity.ok("Eliminado");
        }else{
            return ResponseEntity.status(400).body("Error al eliminar");
        }

    }
}
