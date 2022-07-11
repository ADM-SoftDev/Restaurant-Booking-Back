package com.adm.restaurante.controller;

import com.adm.restaurante.dto.BookingResponses;
import com.adm.restaurante.dto.CreateReservationJsonRest;
import com.adm.restaurante.dto.ReservationDto;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Date;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservatioService;
    @InjectMocks
    ReservationController reservationController;

    CreateReservationJsonRest  createReservationJsonRest = new CreateReservationJsonRest();
    ReservationDto reservationDto = new ReservationDto();

    private static final Long RESERVATION_ID = 1L;
    private static final String LOCATOR = "ADM001";
    public static final String TURN = "Horario 9:00 - 12 :00";

    private static final Long RESTAURANT_ID = 1L;
    private static final Long PERSONS = 1L;
    private static final Date DATE = new Date();
    public static final Long TURN_ID = 1L;

    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String SUCCESS_MESSAGE = "OK";
    private static final String MESSAGE_UPDATE = "RESERVATION UPDATE";
    private static final String MESSAGE_DELETE =  "LOCATOR_DELETED";
    private static final String MESSAGE_CANCEL = "LOCATOR_CANCEL";

    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);

        createReservationJsonRest.setFecha(DATE);
        createReservationJsonRest.setRestaurantId(RESTAURANT_ID);
        createReservationJsonRest.setTurnId(TURN_ID);
        createReservationJsonRest.setPersonas(PERSONS);

        reservationDto.setId(RESERVATION_ID);
        reservationDto.setRestaurantId(RESTAURANT_ID);
        reservationDto.setLocator(LOCATOR);
        reservationDto.setDate(DATE);
        reservationDto.setPersons(PERSONS);
        reservationDto.setTurnoId(TURN);

        Mockito.when(reservatioService.createReservation(createReservationJsonRest)).thenReturn(LOCATOR);
        Mockito.when(reservatioService.getReservation(RESERVATION_ID)).thenReturn(reservationDto);
        Mockito.when(reservatioService.getReservationbyLocator(LOCATOR)).thenReturn(reservationDto);
        Mockito.when(reservatioService.actualizarReserva(reservationDto)).thenReturn(MESSAGE_UPDATE);
        Mockito.when(reservatioService.deleteReservation(RESERVATION_ID)).thenReturn(MESSAGE_DELETE);
        Mockito.when(reservatioService.cancelReservation(LOCATOR)).thenReturn(MESSAGE_CANCEL);
    }


    @Test
    public  void createReservationTest()throws BookingExceptions {
        final BookingResponses<String> response = reservationController.createReservation(createReservationJsonRest);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), LOCATOR);
    }
    @Test
    public  void getReservationByIdTest()throws BookingExceptions {
        final BookingResponses<ReservationDto> responseDto = reservationController.findReservaById(RESERVATION_ID);
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),reservationDto );
    }

    @Test
    public  void getReservaByLocatorTest()throws BookingExceptions {
        final BookingResponses<ReservationDto> responseDto = reservationController.findReservaByLocator(LOCATOR);
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),reservationDto );
    }

    @Test
    public  void updateReservationTest()throws BookingExceptions {
        final BookingResponses<String> response = reservationController.updateReservation(reservationDto);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(),MESSAGE_UPDATE );
    }

    @Test
    public  void eliminarReservationTest()throws BookingExceptions {
        final BookingResponses<String> response = reservationController.eliminarReservation(RESERVATION_ID);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(),MESSAGE_DELETE );
    }

    @Test
    public  void getReservationCancelTest()throws BookingExceptions {
        final BookingResponses<String> response = reservationController.getReservationCancel(LOCATOR);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(),MESSAGE_CANCEL );
    }

}
