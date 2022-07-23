package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Ciudad;
import com.example.demo.entity.Cliente;
import com.example.demo.service.CiudadService;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientConroller {
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CiudadService ciudadService;
	
	@GetMapping("/all")
	public String listarClientes(Model model) {
		
		model.addAttribute("titulo", "listado de clientes");
		List<Cliente> listarClientes = clienteService.listarClientes();
		model.addAttribute("clientes", listarClientes);
		
		
		return "/views/clientes/clientes";
	}
	
	@GetMapping("/new")
	public String agregarClient(Model model) {
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listarCiudades();
		model.addAttribute("titulo", "Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/editCliente";
	}
	
	@PostMapping("/save")
	public String guardar ( @Valid @ModelAttribute Cliente  cliente, BindingResult result, Model model, RedirectAttributes attributes) {
		List<Ciudad> listCiudades = ciudadService.listarCiudades();
		String mensaje ="";
		if(cliente.getId()==null) {
			 mensaje ="Nuevo Cliente";
		}else {mensaje ="Editar Cliente";}
		
		if (result.hasErrors()) {
			model.addAttribute("ciudades", listCiudades);
			model.addAttribute("titulo", mensaje);
			/*
			model.addAttribute("cliente", cliente);
			*/
			return "/views/clientes/editCliente";
		}
		clienteService.guardar(cliente);
		attributes.addFlashAttribute("success", "Cliente agregado con exito");
		return "redirect:/clientes/all";
	}
	
	@GetMapping("/edit/{id}")
	public String editClient(@PathVariable ("id") Long id , Model model, RedirectAttributes attributes) {
		Cliente cliente = clienteService.buscarById(id);
		if (id<=0 || cliente==null) {
			attributes.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/clientes/all";
		}
		
		List<Ciudad> listCiudades = ciudadService.listarCiudades();
		model.addAttribute("titulo", "Editar  Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/editCliente";
	}

	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable ("id") Long id , RedirectAttributes attributes){
		Cliente cliente = clienteService.buscarById(id);
		if (id<=0 || cliente==null) {
			attributes.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/clientes/all";
		}
		
		clienteService.eliminar(id);
		attributes.addFlashAttribute("warning", "Registro eliminado con exito");
		return "redirect:/clientes/all";
	}
}
