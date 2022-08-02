package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/productos")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public String listarProductos(Model model) {
		model.addAttribute("titulo", "listado de productos");
		List<Product> listarProducts = productService.listarProducts();
		model.addAttribute("productos", listarProducts);
		
		
		return "/views/products/products";
	}
	
	@GetMapping("/new")
	public String agregarProduct(Model model) {
		Product product = new Product();
		model.addAttribute("titulo", "Nuevo Producto");
		model.addAttribute("producto", product);
		return "/views/products/editProduct";
	}
	
	@PostMapping("/save")
	public String guardar ( @Valid @ModelAttribute Product  product, BindingResult result, Model model,
			@RequestParam("file") MultipartFile imagen ,RedirectAttributes attributes) {
		String mensaje ="";
		if(product.getId()==null) {
			 mensaje ="Nuevo Producto";
		}else {mensaje ="Editar Producto";}
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", mensaje);
			
			return "/views/products/editProduct";
		}
		
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//images");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte [] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				product.setImagen(imagen.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		productService.guardar(product);
		attributes.addFlashAttribute("success", "Producto agregado con exito");
		return "redirect:/productos/all";
	}
	
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable ("id") Long id , Model model, RedirectAttributes attributes) {
		Product product = productService.buscarById(id);
		if (id<=0 || product==null) {
			attributes.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/products/all";
		}
		
		model.addAttribute("titulo", "Editar  Producto");
		model.addAttribute("producto", product);
		
		return "/views/products/editProduct";
	}

	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable ("id") Long id , RedirectAttributes attributes){
		Product product = productService.buscarById(id);
		if (id<=0 || product==null) {
			attributes.addFlashAttribute("error", "El producto no existe");
			return "redirect:/productos/all";
		}
		
		productService.eliminar(id);
		attributes.addFlashAttribute("warning", "Registro eliminado con exito");
		return "redirect:/productos/all";
	}

}
