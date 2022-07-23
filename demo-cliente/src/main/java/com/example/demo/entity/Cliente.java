package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="clientes")
@Data
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	private String nombre;
	
	@Column(name = "apellidos")
	@NotEmpty
	private String apellido;
	@NotEmpty
	private String telefono;
	@NotEmpty
	@Email
	private String email;
	
	
	//@Column(name="ciudades")
	@ManyToOne
	@JoinColumn(name = "ciudades")
	private Ciudad ciudad;
	

}
