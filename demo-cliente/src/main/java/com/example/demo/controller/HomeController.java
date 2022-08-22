package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/all")
	public String index() {
		List<String> personas = new ArrayList<String>();
		personas.add("Juan");
		personas.add("fernando");
		return "personas";
	}

}
