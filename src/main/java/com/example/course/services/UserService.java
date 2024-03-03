package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DataBaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Component
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		//return obj.get();
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
		//orElseThrow singifica q tentará lançar o get (status 200), se não ele lança a exceção personalizada q criei!
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException(id); 
		}
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
		
		
		
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id); //instancia o usuário sem mexer no banco de dados
			updateData(entity, obj);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(User entity, User obj) {
		// TODO Auto-generated method stub
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
}
