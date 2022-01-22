package io.github.thejeremias.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjudaController {

	@GetMapping("/ajuda")
	public String ajuda() {
		return "ajuda";
	}
	
}
