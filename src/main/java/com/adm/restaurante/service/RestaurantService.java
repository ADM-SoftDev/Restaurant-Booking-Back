package com.adm.restaurante.service;

import java.util.List;

import com.adm.restaurante.dto.RestaurantJsonRest;

import com.adm.restaurante.dto.RestaurantDto;
import com.adm.restaurante.exceptions.BookingExceptions;


public interface RestaurantService {
	
	RestaurantDto getRestaurantById(Long restaurantId) throws BookingExceptions;
	
	RestaurantDto getRestaurantByName(String  nameRestaurante) throws BookingExceptions;
	
	public List<RestaurantDto> getRestaurants() throws BookingExceptions;
	
	public List<RestaurantDto> getFindRestaurants() throws BookingExceptions;

	public String deleteRestaurantByName(String nameRestaurant)throws BookingExceptions;

	public String altaRestaurante(RestaurantJsonRest addRestaurante)throws BookingExceptions;

	public String actualizarRestaurante(RestaurantDto addRestaurante)throws BookingExceptions;

	public String actualizaPriceRestaurant( String localizador, int precio )throws BookingExceptions;

}
