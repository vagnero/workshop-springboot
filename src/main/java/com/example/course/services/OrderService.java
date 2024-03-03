package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.course.entities.Order;
import com.example.course.repositories.OrderRepository;

@Component
public class OrderService {
	
	@Autowired
	private OrderRepository repository; //"Conexão com o banco de dados"
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
	
}
