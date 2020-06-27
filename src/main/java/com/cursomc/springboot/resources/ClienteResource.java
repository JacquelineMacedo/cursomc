package com.cursomc.springboot.resources;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursomc.springboot.domain.Cliente;
import com.cursomc.springboot.dto.ClienteDTO;
import com.cursomc.springboot.dto.ClienteNewDTO;
import com.cursomc.springboot.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(value="/{id}", method=GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ok().body(obj);
	}
	
	@RequestMapping(method = POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
		Cliente cliente = service.fromDTO(clienteNewDTO);
		cliente = service.insert(cliente);
		URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO categoriaDto, @PathVariable Integer id) {
		Cliente categoria = service.fromDTO(categoriaDto);
		categoria.setId(id);
		categoria = service.update(categoria);
		return noContent().build();
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return noContent().build();
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(toList());
		return ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderby", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ok().body(listDto);
	}
 }
