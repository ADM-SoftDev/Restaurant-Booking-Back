package com.adm.restaurante.services;

import com.adm.restaurante.dto.RestaurantDto;
import com.adm.restaurante.dto.RestaurantJsonRest;
import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.entity.BoardEntity;
import com.adm.restaurante.entity.ReservationEntity;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.entity.TurnEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.service.impl.RestaurantServiceImpl;
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

public class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepository;
    @InjectMocks
    RestaurantServiceImpl restaurantService;

    public static final RestaurantEntity RESTAURANT = new RestaurantEntity();
    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME = "Cevicheria Chiquitete";
    private static final String DESCRIPTION = "Restaurante Peruano";
    private static final String ADDRESS = "pje. de la Radio, 4, Alcobendas";
    private static final String IMAGE = "https://www.facebook.com/Pollos-a-la-brasa-Chiquitete-303801440066894/";
    public static final List<TurnEntity> TURN_LIST = new ArrayList<>();
    public static final List<BoardEntity> BOARD_LIST = new ArrayList<>();
    public static final List<ReservationEntity> RESERVATION_LIST = new ArrayList<>();
    public static final RestaurantEntity restaurant = new RestaurantEntity();
    public static final RestaurantJsonRest RESTAURANT_JSON = new RestaurantJsonRest();
    public static final RestaurantDto RESTAURANT_REST = new RestaurantDto();
    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();
    public static final List<TurnDto> TURN_LIST2 = new ArrayList<>();
    private static final String MESSAGE_UPDATE = "RESTAURANT UPDATE";
    private static final String MESSAGE_ADD = "RESTAURANT ADD";
    private static final String MESSAGE_DELETE = "RESTAURANT_DELETED";


    @Before
    public void init() throws BookingExceptions {
       MockitoAnnotations.initMocks(this);
        RESTAURANT.setId_restaurante(RESTAURANT_ID);
        RESTAURANT.setName(NAME);
        RESTAURANT.setDescripcion(DESCRIPTION);
        RESTAURANT.setDireccion(ADDRESS);
        RESTAURANT.setImagen(IMAGE);
        RESTAURANT.setTurns(TURN_LIST);
        RESTAURANT.setMesas(BOARD_LIST);
        RESTAURANT.setReservas(RESERVATION_LIST);

        RESTAURANT_JSON.setNombre(NAME);
        RESTAURANT_JSON.setDescripcion(DESCRIPTION);
        RESTAURANT_JSON.setDireccion(ADDRESS);
        RESTAURANT_JSON.setImagen(IMAGE);

        RESTAURANT_REST.setId_restaurante(RESTAURANT_ID);
        RESTAURANT_REST.setName(NAME);
        RESTAURANT_REST.setDescripcion(DESCRIPTION);
        RESTAURANT_REST.setDireccion(ADDRESS);
        RESTAURANT_REST.setImagen(IMAGE);
        RESTAURANT_REST.setTurns(TURN_LIST2);
    }

    @Test
    public void getRestaurantByIdTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
        restaurantService.getRestaurantById(RESTAURANT_ID);
    }

    @Test(expected = BookingExceptions.class)
    public void getRestaurantByIdErrorTest()throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
        restaurantService.getRestaurantById(RESTAURANT_ID);
        fail();
    }

    @Test
    public void getRestaurantsAllTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
        final List<RestaurantDto> response = restaurantService.getRestaurants();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertEquals(response.size(),1);
    }

    @Test
    public void getRestaurantByNameTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findByName(NAME)).thenReturn(Optional.of(RESTAURANT));
        restaurantService.getRestaurantByName(NAME);
    }
    @Test(expected = BookingExceptions.class)
    public void getRestaurantByNameErrorTest() throws BookingExceptions {
      Mockito.when(restaurantRepository.findByName(NAME)).thenReturn(Optional.empty());
      restaurantService.getRestaurantByName(NAME);
      fail();
    }

    @Test
    public void getFindRestaurantsTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findRestaurantes()).thenReturn(Arrays.asList(restaurant));
        final List<RestaurantDto> listresponse = restaurantService.getFindRestaurants();
        Assert.assertNotNull(listresponse);
        Assert.assertFalse(listresponse.isEmpty());
        Assert.assertEquals(listresponse.size(),1);
    }


    @Test(expected= BookingExceptions.class)
    public void deleteRestaurantByNameInternalServerError() throws BookingExceptions {
        Mockito.when(restaurantRepository.deleteByName(NAME)).thenReturn(Optional.empty());
        Mockito.doThrow(new RuntimeException()).when(restaurantRepository).deleteByName(NAME);
        final String response = restaurantService.deleteRestaurantByName(NAME);
        Assert.assertEquals(response,MESSAGE_DELETE);
        fail();
    }

    @Test
    public void deleteRestaurantByName() throws BookingExceptions {
        Mockito.when(restaurantRepository.deleteByName(NAME)).thenReturn(Optional.empty());
        final String response = restaurantService.deleteRestaurantByName(NAME);
        Assert.assertEquals(response,MESSAGE_DELETE);
    }

    @Test
    public void altaRestauranteTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.save(RESTAURANT)).thenReturn(RESTAURANT);
        Assert.assertEquals(restaurantService.altaRestaurante(RESTAURANT_JSON),MESSAGE_ADD );
    }


    @Test(expected= BookingExceptions.class)
    public void createReservationInternalServerErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);

        Mockito.doThrow(new RuntimeException())
                .when(restaurantRepository)
                .save(Mockito.any(RestaurantEntity.class));

        restaurantService.altaRestaurante(RESTAURANT_JSON);
        fail();
    }

    @Test
    public void actualizarRestauranteTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
        restaurantService.getRestaurantById(RESTAURANT_ID);
        Mockito.when(restaurantRepository.save(RESTAURANT)).thenReturn(RESTAURANT);
        Assert.assertNotNull(restaurantRepository);
        Assert.assertSame(restaurantService.actualizarRestaurante(RESTAURANT_REST),MESSAGE_UPDATE );
    }


    @Test(expected= BookingExceptions.class)
    public void actualzarInternalServerErrorTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);

        Mockito.doThrow(new RuntimeException())
                .when(restaurantRepository)
                .save(Mockito.any(RestaurantEntity.class));

        restaurantService.actualizarRestaurante(RESTAURANT_REST);
        fail();
    }


}
