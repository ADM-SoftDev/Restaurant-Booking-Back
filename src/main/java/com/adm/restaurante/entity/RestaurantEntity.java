package com.adm.restaurante.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


import lombok.Data;

@Entity
@Table (name="RESTAURANTS")
@Data
public class RestaurantEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESTAURANT", unique = true, nullable = false)
	private Long id_restaurante;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String direccion;

	@Column(name = "DESCRIPTION")
	private String descripcion;

	@Column(name = "IMAGE")
	private String imagen;

	@Column(name = "PRICE")
	private Integer pago;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<ReservationEntity> reservas;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<BoardEntity> mesas;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<TurnEntity> turns;

}
