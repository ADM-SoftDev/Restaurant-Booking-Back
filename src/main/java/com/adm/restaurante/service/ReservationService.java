package com.adm.restaurante.service;

import com.adm.restaurante.dto.CreateReservationJsonRest;
import com.adm.restaurante.dto.ReservationDto;
import com.adm.restaurante.exceptions.BookingExceptions;

import javax.mail.MessagingException;

public interface ReservationService {
	
	ReservationDto getReservation(Long reservationId)throws BookingExceptions;

	ReservationDto getReservationbyLocator(String localizador)throws BookingExceptions;

	String createReservation(CreateReservationJsonRest crearReserva) throws BookingExceptions, MessagingException;
	
	String cancelReservation(String localizador) throws BookingExceptions, MessagingException;

	public String actualizarReserva(ReservationDto reservationDto)throws BookingExceptions, MessagingException;

	public void actualizarPago(Boolean payment, String locator)throws BookingExceptions, MessagingException;

	public String deleteReservation(Long reservationId)throws BookingExceptions;
}

