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

import com.cursomc.springboot.domain.Categoria;
import com.cursomc.springboot.dto.CategoriaDTO;
import com.cursomc.springboot.services.CategoriaService;

@RequestMapping(value = "/categorias")
@RestController
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ok().body(obj);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDto) {
		Categoria categoria = service.fromDTO(categoriaDto);
		categoria = service.insert(categoria);
		URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDto, @PathVariable Integer id) {
		Categoria categoria = service.fromDTO(categoriaDto);
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
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(toList());
		return ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderby", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ok().body(listDto);
	}
}