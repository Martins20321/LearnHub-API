package com.martinsdev.learnhub.api.services;

import com.martinsdev.learnhub.api.model.Lesson;
import com.martinsdev.learnhub.api.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id){
        Optional<Lesson> obj = lessonRepository.findById(id);
        return obj.get();
    }
}
