package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.persistence.ProductoRepository;

@RestController
public class ProductoRestController {
	
	
	
	@Autowired
	private ProductoRepository productoDAO; 
	
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> list() {
		
		return new ResponseEntity<List<Producto>>(productoDAO.findAll() ,HttpStatus.OK);
	} 

}