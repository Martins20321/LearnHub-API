package com.estudosjavaspring.springcourse.services;

import com.estudosjavaspring.springcourse.entities.User;
import com.estudosjavaspring.springcourse.repositories.UserRepository;
import com.estudosjavaspring.springcourse.services.exceptions.DatabaseException;
import com.estudosjavaspring.springcourse.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //Onde armazena as operações
    @Autowired
    UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try { //Se não existir
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

    public User update(User obj, Long id){
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
