package com.adm.restaurante.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.adm.restaurante.dto.RestaurantJsonRest;
import com.adm.restaurante.exceptions.InternalServerErrorException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.restaurante.dto.RestaurantDto;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.exceptions.NotFoundException;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public static final ModelMapper modelMaper = new ModelMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);
	
	
	private RestaurantEntity getRestaurantEntity(Long restaurantId) throws BookingExceptions {
		return restaurantRepository.findById(restaurantId).orElseThrow(() ->
				new NotFoundException("ST-404-1", "RESTAURANT_NOT_FOUND"));
	}

	private RestaurantEntity getRestaurantNameEntity(String nameRestaurante)throws BookingExceptions {
		return restaurantRepository.findByName(nameRestaurante).orElseThrow(() ->
				new NotFoundException("ST-404-1", "RESTAURANT_NOT_FOUND"));
	}

	public RestaurantDto getRestaurantById(Long restaurantId) throws BookingExceptions {
		return modelMaper.map(getRestaurantEntity(restaurantId), RestaurantDto.class);
	}

	public List<RestaurantDto> getRestaurants() throws BookingExceptions {
		final List<RestaurantEntity> restaurantEntity = restaurantRepository.findAll();
		return restaurantEntity.stream().map(service -> modelMaper.map(service, RestaurantDto.class))
				.collect(Collectors.toList());
	}

	public RestaurantDto getRestaurantByName(String nameRestaurante) throws BookingExceptions {
		return  modelMaper.map(getRestaurantNameEntity(nameRestaurante), RestaurantDto.class);
	}

	
	public List<RestaurantDto> getFindRestaurants() throws BookingExceptions {
		final List<RestaurantEntity> restaurantListEntity = restaurantRepository.findRestaurantes();
		return restaurantListEntity.stream().map(listar -> modelMaper.map(listar, RestaurantDto.class))
				.collect(Collectors.toList());
	}

	public String deleteRestaurantByName(String nameRestaurant) throws BookingExceptions {
		try {
			restaurantRepository.deleteByName(nameRestaurant);
		} catch (Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ELIMINAR_LA_RESTSAURANT");
		}
		return "RESTAURANT_DELETED";
	}

	public String altaRestaurante(RestaurantJsonRest addRestaurante) throws BookingExceptions {
		final RestaurantEntity restaurant = new RestaurantEntity();
		restaurant.setName(addRestaurante.getNombre());
		restaurant.setDescripcion(addRestaurante.getDescripcion());
		restaurant.setDireccion(addRestaurante.getDireccion());
		restaurant.setImagen(addRestaurante.getImagen());
		try {
			restaurantRepository.save(restaurant);
		}catch(final Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ALMACENAR_LOS_DATOS");
		}
		return "RESTAURANT ADD";
	}

	public String actualizarRestaurante(RestaurantDto updateRestaurante ) throws BookingExceptions {

		RestaurantDto restaurante = this.getRestaurantById(updateRestaurante.getId_restaurante());
		if(restaurante!= null) {
			final RestaurantEntity restaurant = new RestaurantEntity();
			restaurant.setId_restaurante(updateRestaurante.getId_restaurante());
			restaurant.setName(updateRestaurante.getName());
			restaurant.setDescripcion(updateRestaurante.getDescripcion());
			restaurant.setDireccion(updateRestaurante.getDireccion());
			restaurant.setImagen(updateRestaurante.getImagen());
			try {
				restaurantRepository.save(restaurant);
				return "RESTAURANT UPDATE";
			} catch (final Exception e) {
				LOGGER.error("INTERNAL SERVER ERROR", e);
				throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ACTUALIZAR_LOS_DATOS");
			}
		}	else {
			return "RESTAURANT NO FOUNT";
		}

	}

	@Override
	public String actualizaPriceRestaurant(String localizador, int precio) throws BookingExceptions {
		/*RestaurantDto restaurante = this.getRestaurantById(updateRestaurante.getId_restaurante());
		if(restaurante!= null) {
			final RestaurantEntity restaurant = new RestaurantEntity();
			restaurant.setPago(precio);
			try {
				restaurantRepository.save(restaurant);
				return "RESTAURANT UPDATE";
			} catch (final Exception e) {
				LOGGER.error("INTERNAL SERVER ERROR", e);
				throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ACTUALIZAR_LOS_DATOS");
			}
		}	else {*/
			return "RESTAURANT NO FOUNT";
		//}
	}

}
