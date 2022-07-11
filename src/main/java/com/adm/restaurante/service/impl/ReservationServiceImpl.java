package com.adm.restaurante.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.restaurante.dto.CreateReservationJsonRest;
import com.adm.restaurante.dto.ReservationDto;
import com.adm.restaurante.entity.ReservationEntity;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.entity.TurnEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.exceptions.InternalServerErrorException;
import com.adm.restaurante.exceptions.NotFoundException;
import com.adm.restaurante.repository.ReservationRepository;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.repository.TurnRepository;
import com.adm.restaurante.service.ReservationService;


@Service
public class ReservationServiceImpl implements  ReservationService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private TurnRepository turnoRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository; 
	
	public static final ModelMapper modelMaper = new ModelMapper();
	
	private ReservationEntity getReservationEntity(Long reservationId) throws BookingExceptions {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("ST-404-1", "NUM_OF_RESERVA_NOT_FOUND"));

	}

	private ReservationEntity getReservationByNameEntity(String localizador)throws BookingExceptions {
		return reservationRepository.findByLocator(localizador)
				.orElseThrow(() -> new NotFoundException("ST-404-1", "NUM_OF_LOCATOR_NOT_FOUND"));
	}

	@Override
	public ReservationDto getReservation(Long reservationId) throws BookingExceptions {
		return modelMaper.map(getReservationEntity(reservationId), ReservationDto.class);
	}

	@Override
	public ReservationDto getReservationbyLocator(String localizador) throws BookingExceptions {
		return modelMaper.map( getReservationByNameEntity(localizador), ReservationDto.class);
	}

	public String deleteReservation(Long reservationId) throws BookingExceptions {
		reservationRepository.deleteById(reservationId);
		LOGGER.info("Localizado eliminado",reservationId);
		return "LOCATOR_DELETED";
	}

	@Override
	public String createReservation(CreateReservationJsonRest crearReserva) throws BookingExceptions {
		
		final RestaurantEntity restaurantId = restaurantRepository.findById(crearReserva.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_RESTAURANT_NOT_FOUND"));

		final TurnEntity turno = turnoRepository.findById(crearReserva.getTurnId())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_TURN_NOT_FOUND"));
		String locator = generateLocator(restaurantId,crearReserva);

		if (reservationRepository.findByTurnAndRestaurantId(turno.getName(), restaurantId.getId_restaurante()).isPresent()) {
			throw new NotFoundException("RESERVATION_ALREADT_EXIST", "RESERVATION_ALREADT_EXIST");
		}
		
		final ReservationEntity reservation = new ReservationEntity();

		reservation.setLocator(locator);
		reservation.setPersons(crearReserva.getPersonas());
		reservation.setDate(crearReserva.getFecha());
		reservation.setRestaurante(restaurantId);
		reservation.setTurn(turno.getName());
		
		try {
			reservationRepository.save(reservation);
		}catch(final Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ALMACENAR_LOS_DATOS");
		}
		return locator;
	}

	private String generateLocator(final RestaurantEntity restaurantId, final CreateReservationJsonRest createReserva)
			throws BookingExceptions {
		return "BK"+"-"+restaurantId.getName()+"-"+0+createReserva.getTurnId();
	}


	public String cancelReservation(String localizador) throws BookingExceptions {
		this.reservationRepository.findByLocator(localizador)
				.orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND")); 
		try {
			reservationRepository.deleteByLocator(localizador);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ELIMINAR_LA_RESERVA");
		}
		return "LOCATOR_CANCEL";
	}

	public String actualizarReserva(ReservationDto reservationDto ) throws BookingExceptions {

		ReservationDto reserva = this.getReservation(reservationDto.getId());
		final RestaurantEntity restaurantId = restaurantRepository.findById(reservationDto.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_RESTAURANT_NOT_FOUND"));

		if (reserva != null) {
			final ReservationEntity reservat = new ReservationEntity();
			reservat.setId_reservation(reservationDto.getId());
			reservat.setLocator(reservationDto.getLocator());
			reservat.setPersons(reservationDto.getPersons());
			reservat.setDate(reservationDto.getDate());
			reservat.setTurn(reservationDto.getTurnoId());
			reservat.setRestaurante(restaurantId);

			try {
				reservationRepository.save(reservat);
				return "RESERVA UPDATE";
			} catch (final Exception e) {
				LOGGER.error("INTERNAL SERVER ERROR", e);
				throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ACTUALIZAR_LOS_DATOS");
			}
		} else {
			return "RESERVA NO FOUNT";
		}
	}
}
