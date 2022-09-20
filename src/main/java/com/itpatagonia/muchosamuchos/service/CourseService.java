package com.itpatagonia.muchosamuchos.service;

import com.itpatagonia.muchosamuchos.model.Course;
import com.itpatagonia.muchosamuchos.repository.CourseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CourseService implements IABM{
    @Autowired
    CourseRepository courseRepository;
    @Override
    public Optional<?> create(Optional<?> optional) {
        Course course = courseRepository.save((Course)optional.get());
        return Optional.of(course);
    }

    @Override
    public List<?> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<?> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Optional<?> update(Long id, Optional<?> optional) {
        Course courseOld = courseRepository.findById(id).get();
        courseOld.setId(((Course)optional.get()).getId());
        courseOld.setName(((Course)optional.get()).getName());
        courseOld.setDescription(((Course)optional.get()).getDescription());
        courseOld.setHours(((Course)optional.get()).getHours());
        return Optional.of(courseRepository.save(courseOld));
    }

    @Override
    public Boolean delete(Long id) {
        Course course = courseRepository.findById(id).get();
        try {
            courseRepository.delete(course);
            return true;
        }catch (Exception exception){
            log.error("Error al eliminar un course ", exception);
            return false;
        }

    }
}
