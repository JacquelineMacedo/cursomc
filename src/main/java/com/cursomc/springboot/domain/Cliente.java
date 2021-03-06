package com.cursomc.springboot.domain;

import static com.cursomc.springboot.domain.enums.Perfil.CLIENTE;
import static com.cursomc.springboot.domain.enums.TipoCliente.toEnum;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cursomc.springboot.domain.enums.Perfil;
import com.cursomc.springboot.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = { "nome", "email", "cpfOuCnpj", "tipo", "enderecos", "telefones", "pedidos" })
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	public Cliente() {
		addPerfil(CLIENTE);
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;

	@JsonIgnore
	private String senha;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "cliente", cascade = ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();

	@ElementCollection(fetch = EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<Integer>();

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.senha = senha;
		addPerfil(CLIENTE);
	}

	public TipoCliente getTipo() {
		return toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}
