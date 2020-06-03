package com.cursomc.springboot.domain;

import static com.cursomc.springboot.domain.enums.EstadoPagamento.toEnum;
import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.cursomc.springboot.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy = JOINED)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "estado", "pedido" })
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer estado;

	@MapsId
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public EstadoPagamento getEstado() {
		return toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
}
