package com.martinsdev.learnhub.api.repositories;

import com.martinsdev.learnhub.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
