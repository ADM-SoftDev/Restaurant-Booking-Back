package com.adm.restaurante.controller;

import com.adm.restaurante.dto.BookingResponses;
import com.adm.restaurante.dto.RestaurantDto;
import com.adm.restaurante.dto.RestaurantJsonRest;
import com.adm.restaurante.dto.TurnDto;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.service.RestaurantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;



public class RestaurantControllerTest {

    @Mock
    RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @InjectMocks
    RestauranteController restauranteController;

    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME = "Cevicheria Chiquitete";
    private static final String DESCRIPTION = "Restaurante Peruano";
    private static final String ADDRESS = "pje. de la Radio, 4, Alcobendas";
    private static final String IMAGE = "https://www.facebook.com/Pollos-a-la-brasa-Chiquitete-303801440066894/";

    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String SUCCESS_MESSAGE = "OK";
    private static final String MESSAGE_UPDATE = "RESTAURANT UPDATE";
    private static final String MESSAGE_ADD = "RESTAURANT ADD";

    public static final RestaurantDto RESTAURANT_REST = new RestaurantDto();
    public static final RestaurantJsonRest RESTAURANT_JSON = new RestaurantJsonRest();
    public static final List<TurnDto> TURN_LIST = new ArrayList<>();
    public static final List<RestaurantDto> RESTAURANT_LIST_REST = new ArrayList<>();



    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);
        RESTAURANT_REST.setId_restaurante(RESTAURANT_ID);
        RESTAURANT_REST.setName(NAME);
        RESTAURANT_REST.setDescripcion(DESCRIPTION);
        RESTAURANT_REST.setDireccion(ADDRESS);
        RESTAURANT_REST.setImagen(IMAGE);
        RESTAURANT_REST.setTurns(TURN_LIST);

        RESTAURANT_JSON.setNombre(NAME);
        RESTAURANT_JSON.setDescripcion(DESCRIPTION);
        RESTAURANT_JSON.setDireccion(ADDRESS);
        RESTAURANT_JSON.setImagen(IMAGE);

        Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
        Mockito.when(restaurantService.getRestaurantByName(NAME)).thenReturn(RESTAURANT_REST);
        Mockito.when(restaurantService.actualizarRestaurante(RESTAURANT_REST)).thenReturn(MESSAGE_UPDATE);
        Mockito.when(restaurantService.altaRestaurante(RESTAURANT_JSON)).thenReturn(MESSAGE_ADD);

    }

    @Test
    public void getRestaurantByIdTest() throws BookingExceptions {
            final BookingResponses<RestaurantDto> response = restauranteController.getRestaurantById(RESTAURANT_ID);
            Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
            Assert.assertEquals(response.getCode(), SUCCESS_CODE);
            Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
            Assert.assertEquals(response.getData(), RESTAURANT_REST);
    }

    @Test
    public void getRestaurantsTest () throws BookingExceptions {
        final BookingResponses<List<RestaurantDto>> listResponse = restauranteController.getRestaurantList();
        Assert.assertEquals(listResponse.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(listResponse.getCode(), SUCCESS_CODE);
        Assert.assertEquals(listResponse.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(listResponse.getData(), RESTAURANT_LIST_REST);

    }

    @Test
    public void getRestaurantByNameTest()throws BookingExceptions {
        final BookingResponses<RestaurantDto> response = restauranteController.getRestaurantByName(NAME);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), RESTAURANT_REST);
    }

    @Test
    public void getFindRestaurantsTest()throws BookingExceptions {
        final BookingResponses<List<RestaurantDto>> listResponse = restauranteController.getFindRestaurants();
        Assert.assertEquals(listResponse.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(listResponse.getCode(), SUCCESS_CODE);
        Assert.assertEquals(listResponse.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(listResponse.getData(), RESTAURANT_LIST_REST);
    }

    @Test
    public void eliminarRestauranteTest()throws BookingExceptions {
        final BookingResponses<String> response = restauranteController.eliminarRestaurante(NAME);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
    }

    @Test
    public void  actualizarRestauranteTest()throws BookingExceptions {
        final BookingResponses<String> response = restauranteController.updateRestaurante(RESTAURANT_REST);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), MESSAGE_UPDATE);
    }

    @Test
    public void createRestauranteTest() throws BookingExceptions {
        final BookingResponses<String> response = restauranteController.createRestaurante(RESTAURANT_JSON);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(response.getData(), MESSAGE_ADD);
    }


}
