package com.example.demo.repository;

import com.example.demo.entity.Ciudad;

import lombok.val;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ciudad c = new Ciudad();
		c.setCiudad("Toluca");
	String ejemplo = c.getCiudad();
		System.out.println("nombre de la ciudad: "+ejemplo);
		
	}

}
