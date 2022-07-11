package com.adm.restaurante.services;

import com.adm.restaurante.dto.CreateReservationJsonRest;
import com.adm.restaurante.dto.ReservationDto;
import com.adm.restaurante.entity.BoardEntity;
import com.adm.restaurante.entity.ReservationEntity;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.entity.TurnEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.repository.ReservationRepository;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.repository.TurnRepository;
import com.adm.restaurante.service.impl.ReservationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;

public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;
    @Mock
    TurnRepository turnoRepository;
    @Mock
    RestaurantRepository restaurantRepository;
    @Mock
    ReservationDto reservationDto;
    @InjectMocks
    ReservationServiceImpl reservationService;

    public static final ReservationEntity RESERVATION = new ReservationEntity();
    public static final RestaurantEntity RESTAURANT = new RestaurantEntity();
    public static final TurnEntity TURNO_REST = new TurnEntity();
    public static final CreateReservationJsonRest RESERVATION_REST = new CreateReservationJsonRest();
    public static final ReservationDto RESERVATION_DTO = new ReservationDto();

    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
    public static final Optional<TurnEntity> OPTIONAL_TURN = Optional.of(TURNO_REST);
    public static final Optional<ReservationEntity> OPTIONAL_RESERVATION = Optional.of(RESERVATION);
    public static final Optional<ReservationDto> OPTIONAL_RESERVATION_DTO = Optional.of(RESERVATION_DTO);

    public static final Optional<ReservationEntity> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();
    public static final Optional<TurnEntity> OPTIONAL_TURN_EMPTY = Optional.empty();
    public static final Optional<ReservationDto> OPTIONAL_RESERVATION_DTO_EMPTY = Optional.empty();


    private static final Long RESERVATION_ID = 1L;
    private static final String LOCATOR = "ADM001";
    public static final String TURN = "Horario 9:00 - 12 :00";
    private static final Long RESTAURANT_ID = 1L;
    private static final Long PERSONS = 1L;
    private static final Date DATE = new Date();

    public static final Long TURN_ID = 2L;

    private static final String NAME = "Cevicheria Chiquitete";
    private static final String DESCRIPTION = "Restaurante Peruano";
    private static final String ADDRESS = "pje. de la Radio, 4, Alcobendas";
    private static final String IMAGE = "https://www.facebook.com/Pollos-a-la-brasa-Chiquitete-303801440066894/";
    public static final List<TurnEntity> TURN_LIST = new ArrayList<>();
    public static final List<BoardEntity> BOARD_LIST = new ArrayList<>();
    public static final List<ReservationEntity> RESERVATION_LIST = new ArrayList<>();

    private static final String MESSAGE_NOT_UPDATE = "RESERVA NO FOUNT";
    private static final String MESSAGE_DELETE = "LOCATOR_CANCEL";


    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);

        RESTAURANT.setId_restaurante(RESTAURANT_ID);
        RESTAURANT.setName(NAME);
        RESTAURANT.setDescription(DESCRIPTION);
        RESTAURANT.setAddress(ADDRESS);
        RESTAURANT.setImage(IMAGE);
        RESTAURANT.setTurnos(TURN_LIST);
        RESTAURANT.setMesas(BOARD_LIST);
        RESTAURANT.setReservas(RESERVATION_LIST);

        RESERVATION.setId_reservation(RESERVATION_ID);
        RESERVATION.setRestaurante(RESTAURANT);
        RESERVATION.setLocator(LOCATOR);
        RESERVATION.setDate(DATE);
        RESERVATION.setPersons(PERSONS);
        RESERVATION.setTurn(TURN);

        RESERVATION_DTO.setTurnoId(RESERVATION.getTurn());
        RESERVATION_DTO.setPersons(RESERVATION.getPersons());
        RESERVATION_DTO.setId(RESERVATION.getId_reservation());
        RESERVATION_DTO.setRestaurantId(RESERVATION_ID);
        RESERVATION_DTO.setLocator(RESERVATION.getLocator());
        RESERVATION_DTO.setDate(RESERVATION.getDate());

        RESERVATION_REST.setPersonas(PERSONS);
        RESERVATION_REST.setTurnId(TURN_ID);
        RESERVATION_REST.setFecha(DATE);
        RESERVATION_REST.setRestaurantId(RESTAURANT_ID);

        TURNO_REST.setID_TURN(TURN_ID);
        TURNO_REST.setName(NAME);
        TURNO_REST.setRestaurante(RESTAURANT);

    }

    @Test
    public void getReservationTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.of(RESERVATION));
        reservationService.getReservation(RESERVATION_ID);
    }

    @Test(expected = BookingExceptions.class)
    public void getReservationErrorTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.empty());
        reservationService.getReservation(RESERVATION_ID);
        fail();
    }

    @Test
    public void getReservationbyLocatorTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        reservationService.getReservationbyLocator(LOCATOR);
    }

    @Test(expected = BookingExceptions.class)
    public void getReservationbyLocatorErrorTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.empty());
        reservationService.getReservationbyLocator(LOCATOR);
        fail();
    }

    @Test
    public void createReservationTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnoRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURNO_REST.getName(), RESTAURANT
                .getId_restaurante())).thenReturn(OPTIONAL_RESERVATION_EMPTY);
        Mockito.when(reservationRepository.save(Mockito.any(ReservationEntity.class))).thenReturn(new ReservationEntity());
        reservationService.createReservation(RESERVATION_REST);
    }

    @Test(expected = BookingExceptions.class)
    public void createReservationByIDErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
        reservationService.createReservation(RESERVATION_REST);
        fail();
    }

    @Test(expected = BookingExceptions.class)
    public void createReservationByIDTurnoErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnoRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN_EMPTY);
        reservationService.createReservation(RESERVATION_REST);
        fail();
    }

    @Test(expected = BookingExceptions.class)
    public void createReservationByIDTurnoAndRestaurantErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnoRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURNO_REST.getName(), RESTAURANT
                .getId_restaurante())).thenReturn(OPTIONAL_RESERVATION);
        reservationService.createReservation(RESERVATION_REST);
        fail();
    }

    @Test(expected= BookingExceptions.class)
    public void createReservationInternalServerErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnoRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURNO_REST.getName(), RESTAURANT
                .getId_restaurante())).thenReturn(OPTIONAL_RESERVATION_EMPTY);

        Mockito.doThrow(new RuntimeException())
                .when(reservationRepository)
                .save(Mockito.any(ReservationEntity.class));

        reservationService.createReservation(RESERVATION_REST);
        fail();
    }

    @Test
    public void deleteReservationTest() throws BookingExceptions {
        Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(Optional.empty());
        reservationService.deleteReservation(RESERVATION_ID);
    }

    @Test
    public void cancelReservationTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(Optional.empty());
        final String response = reservationService.cancelReservation(LOCATOR);
        Assert.assertEquals(response,MESSAGE_DELETE);
    }

    @Test(expected = BookingExceptions.class)
    public void cancelReservationFindByLocatorErrorTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.empty());
        final String response = reservationService.cancelReservation(LOCATOR);
        Assert.assertEquals(response,MESSAGE_DELETE);
        fail();
    }

    @Test(expected = BookingExceptions.class)
    public void cancelReservationNotfoundErrorTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION_EMPTY);
        Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(Optional.empty());
        final String response = reservationService.cancelReservation(LOCATOR);
        Assert.assertEquals(response,MESSAGE_DELETE);
        fail();
    }


    @Test(expected = BookingExceptions.class)
    public void cancelReservationInternaServerErrorTest() throws BookingExceptions {
        Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.doThrow(new RuntimeException()).when(reservationRepository).deleteByLocator(LOCATOR);
        final String response = reservationService.cancelReservation(LOCATOR);
        Assert.assertEquals(response,MESSAGE_DELETE);
        fail();
    }


    @Test
    public void actualizarReservaTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.of(RESERVATION));

        Mockito.when(reservationRepository.save(Mockito.any(ReservationEntity.class))).thenReturn(new ReservationEntity());
        reservationService.actualizarReserva(RESERVATION_DTO);

    }

    @Test(expected = BookingExceptions.class)
    public void actualizarReservaByIDErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
        reservationService.actualizarReserva(RESERVATION_DTO);
        fail();
    }

    @Test(expected= BookingExceptions.class)
    public void updateReservationInternalServerError() throws BookingExceptions {;
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.of(RESERVATION));

        Mockito.doThrow(new RuntimeException())
                .when(reservationRepository)
                .save(Mockito.any(ReservationEntity.class));

        final String response = reservationService.actualizarReserva(RESERVATION_DTO);
        Assert.assertSame(response ,MESSAGE_NOT_UPDATE );
        fail();
    }

    @Test(expected= BookingExceptions.class)
    public void updateReservationError() throws BookingExceptions {;
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.empty());

        Mockito.doThrow(new RuntimeException())
                .when(reservationRepository)
                .save(Mockito.any(ReservationEntity.class));

        final String response = reservationService.actualizarReserva(RESERVATION_DTO);
        Assert.assertSame(response ,MESSAGE_NOT_UPDATE );
        fail();
    }


}