package com.cursomc.springboot.dto;

import java.io.Serializable;

import com.cursomc.springboot.domain.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO (Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
}
