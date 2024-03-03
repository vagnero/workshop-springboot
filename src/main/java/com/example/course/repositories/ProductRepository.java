package com.example.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course.entities.Product;


/*JpaRepository é uma interface fornecida pelo Spring Data JPA. 
 * Ela fornece métodos genéricos para operações de persistência 
 * em um banco de dados.
*/
public interface ProductRepository extends JpaRepository<Product, Long> {

}

//Ou seja, ela fornece métodos prontos para interagir com o banco de dados
//Não é necessário implementar esses métodos manualmente, o springboot já o faz em tempo de excecução!