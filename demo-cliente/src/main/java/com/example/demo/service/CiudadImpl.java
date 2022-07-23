package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Ciudad;
import com.example.demo.repository.CiudadRepository;

@Service
public class CiudadImpl implements CiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;

	@Override
	public List<Ciudad> listarCiudades() {
		
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
