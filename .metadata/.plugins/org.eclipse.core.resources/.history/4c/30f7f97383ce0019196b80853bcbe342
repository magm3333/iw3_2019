package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IProductoBusiness;
import com.example.demo.model.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	
	@Autowired
	private IProductoBusiness productoBusiness; 
	

	
	@GetMapping("")
	public ResponseEntity<List<Producto>> list() {
		
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.list() ,HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 

}
