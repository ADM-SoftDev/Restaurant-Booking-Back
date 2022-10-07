package com.adm.restaurante.controller;

import java.util.List;
import com.adm.restaurante.dto.RestaurantJsonRest;
import com.adm.restaurante.dto.RestaurantDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.adm.restaurante.dto.BookingResponses;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/booking-restaurant" + "/v1")
@Tag(name="booking-restaurant", description="Catalogo de Restaurantes")
public class RestauranteController {
	
	@Autowired
	RestaurantService restaurantService;

	@Operation(summary = "Obtener Id de Restaurantes")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Existe Restaurantes", content = @Content(schema = @Schema(implementation = RestaurantDto.class))),
			@ApiResponse(responseCode = "404", description = "No se encontro Restaurantes", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
	})
	@GetMapping(value = "restaurant" + "/{" + "restaurantId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId)
			throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
				this.restaurantService.getRestaurantById(restaurantId));
	}

	@Operation(summary = "Obtener Lista de Restaurantes")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "restaurants" , produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<List<RestaurantDto>> getRestaurantList()throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
		restaurantService.getRestaurants());
	}

	@Operation(summary = "Obtener Datos de Restaurante por Nombre")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Existe el Restaurante", content = @Content(schema = @Schema(implementation = RestaurantDto.class))),
			@ApiResponse(responseCode = "404", description = "No se encontro Restaurante solicitado", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
	})
	@GetMapping(value = "DatosPorNombreRest", produces =  MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<RestaurantDto> getRestaurantByName(@RequestParam String restaurantName)
			throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
				this.restaurantService.getRestaurantByName(restaurantName));
	}

	@Operation(summary = "Obtener Listado de Restaurantes")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "Listarestaurants" , produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<List<RestaurantDto>> getFindRestaurants()throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
				restaurantService.getFindRestaurants());
	}
	@Operation(summary = "Delete Restaurante")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha eliminado los Datos", content = @Content(schema = @Schema(implementation = RestaurantJsonRest.class))),
			@ApiResponse(responseCode = "404", description = "No se pudo eliminar los Datos", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
	})
	@DeleteMapping(value = "/eliminarRestaurante", produces =  MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<String> eliminarRestaurante(@RequestParam  String restaurantName)throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
				this.restaurantService.deleteRestaurantByName(restaurantName));
	}


	@Operation(summary = "Actualizar Restaurante")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Existe Restaurantes", content = @Content(schema = @Schema(implementation = RestaurantDto.class))),
			@ApiResponse(responseCode = "404", description = "No se encontro Restaurantes", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
	})
	@PutMapping(value = "/updateRestaurante", produces =  MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<String> updateRestaurante( @RequestBody RestaurantDto updateRestaurante )
			throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
				this.restaurantService.actualizarRestaurante(updateRestaurante));
	}
	@Operation(summary = "ADD Restaurante")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Existe Restaurantes", content = @Content(schema = @Schema(implementation = RestaurantJsonRest.class))),
			@ApiResponse(responseCode = "404", description = "No se encontro Restaurantes", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
	})
	@PostMapping(value = "/crearRestaurante", produces =  MediaType.APPLICATION_JSON_VALUE)
	public BookingResponses<String> createRestaurante(@RequestBody @Validated RestaurantJsonRest createRestaurante)
			throws BookingExceptions {
		return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
				this.restaurantService.altaRestaurante(createRestaurante));
	}

}
