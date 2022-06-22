package com.adm.restaurante.controller;

import com.adm.restaurante.dto.BoardDto;
import com.adm.restaurante.dto.BookingResponses;
import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.dto.TurnJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.TurnService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TunoControllerTest {

    @Mock
    private TurnService turnoService;
    @InjectMocks
    TurnoController turnoController;

    private static final Long TURNO_ID = 1L;
    private static final String NAME = "Horario Mañana";
    private static final Long RESTAURANT_ID = 1L;

    TurnDto turnoDto = new TurnDto();
    TurnJsonRest turnoJsonrest = new TurnJsonRest();
    public static final List<TurnDto> RESTAURANT_LIST_TURNO = new ArrayList<>();

    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String SUCCESS_MESSAGE = "OK";
    private static final String MESSAGE_ADD = "TURN ADD";
    private static final String MESSAGE_UPDATE = "TURN UPDATE";
    private static final String MESSAGE_DELETE = "SE Ha Eliminado Correctamente el turno";


    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);

        turnoDto.setId(TURNO_ID);
        turnoDto.setName(NAME);
        turnoDto.setRestaurantId(RESTAURANT_ID);

        turnoJsonrest.setTurno(NAME);
        turnoJsonrest.setRestaurantId(RESTAURANT_ID);

        Mockito.when(turnoService.getTurno(TURNO_ID)).thenReturn(turnoDto);
        Mockito.when(turnoService.listTurn()).thenReturn(RESTAURANT_LIST_TURNO);
        Mockito.when(turnoService.addTurn(turnoJsonrest)).thenReturn(MESSAGE_ADD);
        Mockito.when(turnoService.updateTurn(turnoDto)).thenReturn(MESSAGE_UPDATE);
        Mockito.when(turnoService.deleteTurn(TURNO_ID)).thenReturn(MESSAGE_DELETE);
    }

    @Test
    public void findTurnoByIdTest() throws BookingExceptions {
        final BookingResponses<TurnDto> response = turnoController.findTurnoById(TURNO_ID);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), turnoDto);
    }

    @Test
    public void listarTurnoTest() throws BookingExceptions {
        final BookingResponses<List<TurnDto>> response = turnoController.listarTurno();
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), RESTAURANT_LIST_TURNO);
    }

    @Test
    public void createTurnoTest() throws BookingExceptions {
        final BookingResponses<String> response = turnoController.createTurno(turnoJsonrest);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), MESSAGE_ADD);

    }

    @Test
    public void updateTurnoTest() throws BookingExceptions {
        final BookingResponses<String> response = turnoController.updateTurno(turnoDto);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), MESSAGE_UPDATE);

    }

    @Test
    public void eliminarTurnoTest()  throws BookingExceptions {
        final BookingResponses<String> response = turnoController.eliminarTurno(TURNO_ID);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), MESSAGE_DELETE);
    }

}
