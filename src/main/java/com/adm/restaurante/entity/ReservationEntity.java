package com.adm.restaurante.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table (name="RESERVATION")
@Data
public class ReservationEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESERVATION", unique = true, nullable = false)
	private Long id_reservation;
	
	@Column(name = "LOCATOR")
	private String locator;
	
	@Column(name = "PERSONS")
	private Long persons;
	
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "TURN")
	private String turn;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "RESTAURANTE_ID", nullable = false)
	private RestaurantEntity restaurante;
	
	

}
