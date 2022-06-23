package com.adm.restaurante.service.impl;

import com.adm.restaurante.dto.TurnJsonRest;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.exceptions.InternalServerErrorException;
import com.adm.restaurante.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.entity.TurnEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.exceptions.NotFoundException;
import com.adm.restaurante.repository.TurnRepository;
import com.adm.restaurante.service.TurnService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnServiceImpl implements TurnService {

	@Autowired
	private TurnRepository turnoRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TurnServiceImpl.class);

	public static final ModelMapper modelMaper = new ModelMapper();
	
	private TurnEntity getTurnoEntity(Long turnoId) throws BookingExceptions {
		return turnoRepository.findById(turnoId).orElseThrow(() -> new NotFoundException("ST-404-1", "TURN_ID_NOT_FOUND"));
	}

	@Override
	public TurnDto getTurno(Long turnoId) throws BookingExceptions {
		return modelMaper.map(getTurnoEntity(turnoId), TurnDto.class);
	}

	@Override
	public List<TurnDto> listTurn() throws BookingExceptions {
		final List<TurnEntity> listTurno = turnoRepository.findAll();
		return listTurno.stream().map(service ->modelMaper.map(service, TurnDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public String deleteTurn(Long turnoId) throws BookingExceptions {
		turnoRepository.deleteById(turnoId);
		LOGGER.info("Localizado eliminado el turno",turnoId);
		return "SE Ha Eliminado Correctamente el turno";
	}


	public String addTurn(TurnJsonRest addTurno) throws BookingExceptions {
		final RestaurantEntity restaurantId = restaurantRepository.findById(addTurno.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_RESTAURANT_NOT_FOUND"));
		final TurnEntity turnoEntity = new TurnEntity();
		turnoEntity.setName(addTurno.getTurno());
		turnoEntity.setRestaurante(restaurantId);
		try {
			turnoRepository.save(turnoEntity);
		}catch(final Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ALMACENAR_LOS_DATOS");
		}
		return "TURN ADD";
	}


	public String updateTurn(TurnDto updateTurno) throws BookingExceptions {
		final RestaurantEntity restaurantId = restaurantRepository.findById(updateTurno.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_RESTAURANT_NOT_FOUND"));
		TurnDto turno = this.getTurno(updateTurno.getId());
		if(turno!=null){
			final TurnEntity turnoEntity = new TurnEntity();
			turnoEntity.setID_TURN(updateTurno.getId());
			turnoEntity.setName(updateTurno.getName());
			turnoEntity.setRestaurante(restaurantId);
			try {
				turnoRepository.save(turnoEntity);
				return "TURNO UPDATE";
			}catch(final Exception e) {
				LOGGER.error("INTERNAL SERVER ERROR", e);
				throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ALMACENAR_LOS_DATOS");
			}

		}else {
			return "TURNO NO FOUNT";
		}

	}


}
