package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
@Service
public class ClienteImpl implements ClienteService{
	
	
	@Autowired
	ClienteRepository clienteRepository;
	@Override
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return  (List<Cliente>)clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		clienteRepository.save(cliente);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente buscarById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		clienteRepository.deleteById(id);;
		
	}

}
