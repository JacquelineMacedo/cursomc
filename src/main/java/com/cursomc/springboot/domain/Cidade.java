package com.cursomc.springboot.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(exclude={"nome", "estado"})
@NoArgsConstructor 
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Cidade(Integer id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;
}
