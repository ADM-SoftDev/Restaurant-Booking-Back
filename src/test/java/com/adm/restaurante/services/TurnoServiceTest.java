package com.adm.restaurante.services;

import com.adm.restaurante.controller.TurnoController;
import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.dto.TurnJsonRest;
import com.adm.restaurante.entity.BoardEntity;
import com.adm.restaurante.entity.ReservationEntity;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.entity.TurnEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.repository.TurnRepository;
import com.adm.restaurante.service.TurnService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;

public class TurnoServiceTest {

    @Mock
    TurnService turnoService;
    @Mock
    TurnRepository turnoRepository;
    @InjectMocks
    TurnoController turnoController;
    @Mock
    RestaurantRepository restaurantRepository;

    private static final Long TURNO_ID = 1L;
    private static final String NAME = "Horario Mañana";
    private static final String MESSAGE_DELETE = "SE Ha Eliminado Correctamente el turno";
    private static final String MESSAGE_ADD = "TURN ADD";
    private static final String MESSAGE_UPDATE = "TURNO UPDATE";

    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME_RESTAURANT = "Cevicheria Chiquitete";
    private static final String DESCRIPTION = "Restaurante Peruano";
    private static final String ADDRESS = "pje. de la Radio, 4, Alcobendas";
    private static final String IMAGE = "https://www.facebook.com/Pollos-a-la-brasa-Chiquitete-303801440066894/";
    public static final List<TurnEntity> TURN_LIST = new ArrayList<>();
    public static final List<BoardEntity> BOARD_LIST = new ArrayList<>();
    public static final List<ReservationEntity> RESERVATION_LIST = new ArrayList<>();

    public static final TurnEntity TURNO = new TurnEntity();
    public static final RestaurantEntity RESTAURANT = new RestaurantEntity();

    public static final TurnJsonRest TURNOJSONREST = new TurnJsonRest();
    public static final TurnDto TURNODTO = new TurnDto();
    public static final Optional<TurnEntity> OPTIONAL_TURNO = Optional.of(TURNO);
    public static final Optional<TurnEntity> OPTIONAL_TURNO_EMPTY = Optional.empty();
    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();
    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);

    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);

        RESTAURANT.setId_restaurante(RESTAURANT_ID);
        RESTAURANT.setName(NAME_RESTAURANT);
        RESTAURANT.setDescription(DESCRIPTION);
        RESTAURANT.setAddress(ADDRESS);
        RESTAURANT.setImage(IMAGE);
        RESTAURANT.setTurnos(TURN_LIST);
        RESTAURANT.setMesas(BOARD_LIST);
        RESTAURANT.setReservas(RESERVATION_LIST);

        TURNO.setName(NAME);
        TURNO.setRestaurante(RESTAURANT);
        TURNO.setID_TURN(TURNO_ID);

        TURNOJSONREST.setTurno(NAME);
        TURNOJSONREST.setRestaurantId(RESTAURANT_ID);

        TURNODTO.setId(TURNO_ID);
        TURNODTO.setRestaurantId(RESTAURANT_ID);
        TURNODTO.setName(NAME);


    }

    @Test
    public void getTurnoTest() throws BookingExceptions {
        Mockito.when(turnoRepository.findById(TURNO_ID)).thenReturn(Optional.of(TURNO));
        turnoService.getTurno(TURNO_ID);
    }
    /* TES da FAllido*/
    @Test(expected = BookingExceptions.class)
    public void getTurnoErrorNotFound() throws BookingExceptions {
        Mockito.when(turnoRepository.findById(TURNO_ID)).thenReturn(Optional.empty());
        turnoService.getTurno(TURNO_ID);
        fail();
    }

    @Test
    public void listTurnTest()throws BookingExceptions {
        Mockito.when(turnoRepository.findAll()).thenReturn(Arrays.asList(TURNO));
        final List<TurnDto> listaResponse =  turnoService.listTurn();
        Assert.assertNotNull(listaResponse);
        Assert.assertTrue(listaResponse.isEmpty());
        Assert.assertEquals(listaResponse.size(),0);
    }

    @Test
    public void deleteTurnTest() throws BookingExceptions {
        Mockito.when(turnoService.deleteTurn(TURNO_ID)).thenReturn(MESSAGE_DELETE);
    }

    @Test
    public void addTurnTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnoRepository.findById(TURNO_ID)).thenReturn(OPTIONAL_TURNO);
        Mockito.when(turnoRepository.save(Mockito.any(TurnEntity.class))).thenReturn(new TurnEntity());

        turnoService.addTurn(TURNOJSONREST);
    }
    /* TES da FAllido*/
    @Test
    public void addTurnMessageTest() throws BookingExceptions {
        Mockito.when(turnoRepository.save(TURNO)).thenReturn(TURNO);
        Assert.assertEquals(turnoService.addTurn(TURNOJSONREST),MESSAGE_ADD );
    }

    /* TES da FAllido*/
    @Test(expected = BookingExceptions.class)
    public void addTurnNotFoundTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
        Mockito.when(turnoRepository.findById(TURNO_ID)).thenReturn(OPTIONAL_TURNO_EMPTY);

        Mockito.doThrow(Exception.class)
                .when(turnoRepository)
                .save(Mockito.any(TurnEntity.class));

        turnoService.addTurn(TURNOJSONREST);
        fail();
    }

    @Test
    public void updateTurnTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnoRepository.findById(TURNO_ID)).thenReturn(OPTIONAL_TURNO);
        Mockito.when(turnoRepository.save(Mockito.any(TurnEntity.class))).thenReturn(new TurnEntity());
        turnoService.updateTurn(TURNODTO);
    }

    /* TES da FAllido*/
    @Test(expected = BookingExceptions.class)
    public void updateTurnNotFoundTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
        Mockito.when(turnoRepository.findById(TURNO_ID)).thenReturn(OPTIONAL_TURNO_EMPTY);

        Mockito.doThrow(Exception.class)
                .when(turnoRepository)
                .save(Mockito.any(TurnEntity.class));


        turnoService.updateTurn(TURNODTO);
        fail();
    }
}
