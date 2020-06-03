package com.cursomc.springboot.resources;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.springboot.domain.Pedido;
import com.cursomc.springboot.services.PedidoService;

@RequestMapping(value = "/pedidos")
@RestController
public class PedidoResource {
	
	@Autowired
	private PedidoService service;

	@RequestMapping(value="/{id}", method=GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ok().body(obj);
	}
 }
