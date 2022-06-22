package com.adm.restaurante.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


import lombok.Data;

@Entity
@Table (name="RESTAURANTE")
@Data
public class RestaurantEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESTAURANTE", unique = true, nullable = false)
	private Long id_restaurante;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IMAGE")
	private String image;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<ReservationEntity> reservas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<BoardEntity> mesas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurante")
	private List<TurnEntity> turnos;

}
