package com.adm.restaurante.controller;

import com.adm.restaurante.dto.BookingResponses;
import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.dto.TurnJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.TurnService;
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

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/booking-turno" + "/v1")
public class TurnoController {

    @Autowired
    private TurnService serticeTurno;

    @Operation(summary = "buscar por Id por Turno")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Encontrado Datos con el id", content = @Content(schema = @Schema(implementation = TurnDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Encontrar Datos con ese ID", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @GetMapping(value = "/buscarTurno"+ "/{" + "turnoId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<TurnDto> findTurnoById(@PathVariable Long turnoId)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serticeTurno.getTurno(turnoId));
    }

    @Operation(summary = "Listar Turno")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Encontrado Datos para listar", content = @Content(schema = @Schema(implementation = TurnDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Encontrar Datos para listar", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @GetMapping(value = "/listarTurnos", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<List<TurnDto>> listarTurno()
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serticeTurno.listTurn());
    }

    @Operation(summary = "ADD Turno")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha creado correctamente Turno", content = @Content(schema = @Schema(implementation = TurnDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo crear Turno", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @PostMapping(value = "/crearTurno", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> createTurno(@RequestBody @Validated TurnJsonRest createturno)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serticeTurno.addTurn(createturno));
    }

    @Operation(summary = "Update turno")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Actualziado correctamente Turno", content = @Content(schema = @Schema(implementation = TurnDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Actualizar Turno", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @PutMapping(value = "/actualzarTurno", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> updateTurno( @RequestBody TurnDto updateTurno )
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serticeTurno.updateTurn(updateTurno));
    }

    @Operation(summary = "Delete Turno")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Eliminado correctamente Turno", content = @Content(schema = @Schema(implementation = TurnDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Eliminar el Turno", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @DeleteMapping(value = "/eliminarTurno"+ "/{" + "turnoId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> eliminarTurno(@PathVariable Long turnoId)throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serticeTurno.deleteTurn(turnoId));
    }

}
