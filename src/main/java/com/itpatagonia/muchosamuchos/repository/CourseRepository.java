package com.itpatagonia.muchosamuchos.repository;

import com.itpatagonia.muchosamuchos.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
