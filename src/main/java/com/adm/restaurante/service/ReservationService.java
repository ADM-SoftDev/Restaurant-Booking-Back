package com.adm.restaurante.service;

import com.adm.restaurante.dto.CreateReservationJsonRest;
import com.adm.restaurante.dto.ReservationDto;
import com.adm.restaurante.exceptions.BookingExceptions;

public interface ReservationService {
	
	ReservationDto getReservation(Long reservationId)throws BookingExceptions;

	ReservationDto getReservationbyLocator(String localizador)throws BookingExceptions;

	String createReservation(CreateReservationJsonRest crearReserva)throws BookingExceptions;
	
	String cancelReservation(String localizador)throws BookingExceptions;

	public String actualizarReserva(ReservationDto reservationDto)throws BookingExceptions;

	public String deleteReservation(Long reservationId)throws BookingExceptions;
}

