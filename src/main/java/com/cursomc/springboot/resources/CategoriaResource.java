package com.cursomc.springboot.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.springboot.domain.Categoria;

@RequestMapping(value = "/categorias")
@RestController
public class CategoriaResource {

	@RequestMapping(method=GET)
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria("infomatica", 1);
		Categoria cat2 = new Categoria("escritorio",2);
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}
 }
