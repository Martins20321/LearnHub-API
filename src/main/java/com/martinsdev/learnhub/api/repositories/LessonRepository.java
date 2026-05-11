package com.martinsdev.learnhub.api.repositories;

import com.martinsdev.learnhub.api.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
