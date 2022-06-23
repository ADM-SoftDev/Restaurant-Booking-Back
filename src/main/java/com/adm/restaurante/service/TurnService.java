package com.adm.restaurante.service;

import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.dto.TurnJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;

import java.util.List;

public interface TurnService {
	
	TurnDto getTurno(Long turnoId)throws BookingExceptions;

	public List<TurnDto> listTurn() throws BookingExceptions;

	public String deleteTurn(Long turnoId)throws BookingExceptions;

	public String addTurn(TurnJsonRest addTurno)throws BookingExceptions;

	public String updateTurn(TurnDto updateTurno)throws BookingExceptions;


}
