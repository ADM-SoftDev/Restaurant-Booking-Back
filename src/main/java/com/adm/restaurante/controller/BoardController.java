package com.adm.restaurante.controller;

import com.adm.restaurante.dto.*;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.BoardService;
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
@RequestMapping(path = "/booking-board" + "/v1")
public class BoardController {

    @Autowired
    private BoardService serviceBoard;

    @Operation(summary = "buscar por Id por Board")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Encontrado Datos con el id", content = @Content(schema = @Schema(implementation = BoardDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Encontrar Datos con ese ID", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @GetMapping(value = "/buscarBoard"+ "/{" + "boardId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<BoardDto> findBoardById(@PathVariable Long boardId)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serviceBoard.getBoard(boardId));
    }

    @Operation(summary = "Listar Boards")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Encontrado Datos", content = @Content(schema = @Schema(implementation = BoardDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Encontrar Datos", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @GetMapping(value = "/listarBoard", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<List<BoardDto>> listarBoard()
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serviceBoard.listBoards());
    }

    @Operation(summary = "ADD Board")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha creado correctamente Board", content = @Content(schema = @Schema(implementation = BoardDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo crear Board", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @PostMapping(value = "/crearBoard", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> createBoard(@RequestBody @Validated BoardJsonRest createBoard)
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serviceBoard.addBoard(createBoard));
    }

    @Operation(summary = "Update Board")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Actualziado correctamente Board", content = @Content(schema = @Schema(implementation = BoardDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Actualizar Board", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @PutMapping(value = "/actualzarBoard", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> updateBoard(@RequestBody BoardDto updateBoard )
            throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serviceBoard.updateBoard(updateBoard));
    }
    @Operation(summary = "Delete Board")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha Eliminado correctamente Board", content = @Content(schema = @Schema(implementation = BoardDto.class))),
            @ApiResponse(responseCode = "404", description = "No se pudo Eliminar Board", content = @Content(schema = @Schema(implementation = BookingResponses.class)))
    })
    @DeleteMapping(value = "/eliminarBoard"+ "/{" + "boardId" + "}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public BookingResponses<String> eliminarBoard(@PathVariable Long boardId)throws BookingExceptions {
        return new BookingResponses<>("Success", String.valueOf(HttpStatus.OK), "OK",
                this.serviceBoard.deleteBoardById(boardId));
    }

}
