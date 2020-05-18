package com.cursomc.springboot.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/categorias")
@RestController
public class CategoriaResource {

	@RequestMapping(method=GET)
	public String listar() {
		return "Rest está funcionando";
	}
 }
