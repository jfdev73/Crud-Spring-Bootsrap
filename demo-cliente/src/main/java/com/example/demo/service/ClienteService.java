package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Cliente;

public interface ClienteService {

	public List<Cliente> listarClientes();
	
	public void guardar(Cliente cliente);
	
	public Cliente buscarById(Long id);
	
	public void eliminar(Long id);
	
	
}
