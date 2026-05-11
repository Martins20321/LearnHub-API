package com.martinsdev.learnhub.api.services;

import com.martinsdev.learnhub.api.model.Course;
import com.martinsdev.learnhub.api.repositories.CourseRepository;
import com.martinsdev.learnhub.api.infra.exceptions.DatabaseException;
import com.martinsdev.learnhub.api.infra.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> findAll(){
        return repository.findAll();
    }

    public Course findById(Long id){
        Optional<Course> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Course insert(Course obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            if(!repository.existsById(id)) throw new ResourceNotFoundException(id);
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) { //Primeira exception, quando não encontrar o id
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e) { //Segunda exception, erro de integridade
            throw new DatabaseException(e.getMessage());
        }
    }

    public Course update(Long id, Course obj){
        try {
            Course entity = repository.getReferenceById(id);
            UpdateDate(entity, obj);
            return repository.save(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void UpdateDate(Course entity, Course obj) {
        entity.setDescription(obj.getDescription());
        entity.setTitle(obj.getTitle());
        entity.setImgUrl(obj.getImgUrl());
    }
}
