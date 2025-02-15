package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer>{
	
	

}
