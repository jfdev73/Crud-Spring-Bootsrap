package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductImpl  implements ProductService{
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> listarProducts() {
		return  (List<Product>)productRepository.findAll();
	}

	@Override
	public void guardar(Product product) {
		productRepository.save(product);
		
	}

	@Override
	public Product buscarById(Long id) {
		return productRepository.findById(id).orElseGet(null);
	}

	@Override
	public void eliminar(Long id) {
		productRepository.deleteById(id);
		
	}

}
