package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Product;



public interface ProductService {
	
public List<Product> listarProducts();
	
	public void guardar(Product product);
	
	public Product buscarById(Long id);
	
	public void eliminar(Long id);

}
