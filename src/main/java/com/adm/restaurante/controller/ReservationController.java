package com.adm.restaurante.controller;

import com.adm.restaurante.dto.*;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-reservation" + "/v1")
public class ReservationController {

    @Autowired
    private ReservationService reservatioService;

    @Operation(summary = "Obtener una Reserva")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe Reserva", content = @Content(schema = @Schema(implementation = CreateReservationJsonRest.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo obtener la reserva", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @PostMapping(value = "/createReserva", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> createReservation(@RequestBody @Validated CreateReservationJsonRest createReservation)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.reservatioService.createReservation(createReservation));
    }

    @Operation(summary = "Cancelar una Reserva")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminado Correctamente", content = @Content(schema = @Schema(implementation = CreateReservationJsonRest.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo cancelar Reserva", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @DeleteMapping(value = "/cancelarReserva", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> getReservationCancel(@RequestParam  String locator)throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.reservatioService.cancelReservation(locator));
    }

    @Operation(summary = "buscar por Id de Reserva")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "se encontro id Correctamente", content = @Content(schema = @Schema(implementation = CreateReservationJsonRest.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo enconstrar ID Reserva", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @GetMapping(value = "reserva" + "/{" + "reservatId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<ReservationDto> findReservaById(@PathVariable Long reservatId)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.reservatioService.getReservation(reservatId));
    }

    @Operation(summary = "buscar por Id por localizador")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminado Correctamente Localizador", content = @Content(schema = @Schema(implementation = CreateReservationJsonRest.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar Localizador", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @GetMapping(value = "buscarLocalizador"+ "/{" + "localizador" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<ReservationDto> findReservaByLocator(@PathVariable String localizador)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.reservatioService.getReservationbyLocator(localizador));
    }
    @Operation(summary = "Actualizar Reservation")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualziado Correctamente Reserva", content = @Content(schema = @Schema(implementation = CreateReservationJsonRest.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Actualizar Reserva", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @PutMapping(value = "/updateReservation", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> updateReservation(@RequestBody ReservationDto reservationDto  )
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.reservatioService.actualizarReserva(reservationDto));
    }
    @Operation(summary = "Delete Reserva")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Eliminado correctamente Reservation", content = @Content(schema = @Schema(implementation = CreateReservationJsonRest.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Eliminar Reserva", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @DeleteMapping(value = "/eliminarReserva"+ "/{" + "reservationId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> eliminarReservation(@PathVariable Long reservationId)throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.reservatioService.deleteReservation(reservationId));
    }


}
