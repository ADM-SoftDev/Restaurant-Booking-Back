package com.adm.restaurante.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table (name="BOARD")
@Data
public class BoardEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_BOARD", unique = true, nullable = false)
	private Long ID_BOARD;
	
	@Column(name = "CAPACITY")
	private Long capacity;
	
	@Column(name = "NUMBER")
	private Long number;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "RESTAURANT_ID", nullable = false)
	private RestaurantEntity restaurante;

}
