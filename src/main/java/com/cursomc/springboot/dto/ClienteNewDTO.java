package com.cursomc.springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cursomc.springboot.services.validation.ClienteInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ClienteInsert
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Prenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	@Email(message = "Email invalido")
	private String email;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	private String cpfOuCnpj;
	private Integer tipo;

	@NotEmpty(message = "Prenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "Prenchimento obrigatório")
	private String cep;

	@NotEmpty(message = "Prenchimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;

}
