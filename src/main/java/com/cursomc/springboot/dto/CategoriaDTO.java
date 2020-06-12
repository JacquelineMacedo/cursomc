package com.cursomc.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
	
	@NotEmpty(message="Prenchimento obrigatório")
	@Length(min=5, max=80, message= "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO (Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
}
