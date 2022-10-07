package com.adm.restaurante.services;

import com.adm.restaurante.dto.BoardDto;
import com.adm.restaurante.dto.BoardJsonRest;
import com.adm.restaurante.entity.BoardEntity;
import com.adm.restaurante.entity.ReservationEntity;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.entity.TurnEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.repository.BoardRepository;
import com.adm.restaurante.repository.RestaurantRepository;
import com.adm.restaurante.service.impl.BoardServiceImpl;
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

public class BoardServiceTest {

    @Mock
    BoardRepository boardRepository;
    @Mock
    RestaurantRepository restaurantRepository;
    @InjectMocks
    BoardServiceImpl boardService;

    public static final BoardDto BoardRestDto = new BoardDto();
    public static final BoardJsonRest BoardJsonRest = new BoardJsonRest();
    public static final BoardEntity Board = new BoardEntity();
    public static final RestaurantEntity RESTAURANT = new RestaurantEntity();

    private static final Long BOARD_ID = 1L;
    private static final Long CAPACITY = 2L;
    private static final Long NUMBER = 3L;
    private static final Long RESERVATION_ID = 1L;

    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME = "Cevicheria Chiquitete";
    private static final String DESCRIPTION = "Restaurante Peruano";
    private static final String ADDRESS = "pje. de la Radio, 4, Alcobendas";
    private static final String IMAGE = "https://www.facebook.com/Pollos-a-la-brasa-Chiquitete-303801440066894/";
    public static final List<TurnEntity> TURN_LIST = new ArrayList<>();
    public static final List<BoardEntity> BOARD_LIST = new ArrayList<>();
    public static final List<ReservationEntity> RESERVATION_LIST = new ArrayList<>();

    public static final Optional<BoardEntity> OPTIONAL_Board = Optional.of(Board);
    public static final Optional<BoardEntity> OPTIONAL_Board_Empty = Optional.empty();
    public static final Optional<RestaurantEntity> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);

    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);

        Board.setID_MESA(BOARD_ID);
        Board.setRestaurante(RESTAURANT);
        Board.setCapacidad(CAPACITY);
        Board.setNumero(NUMBER);

        RESTAURANT.setId_restaurante(RESTAURANT_ID);
        RESTAURANT.setName(NAME);
        RESTAURANT.setDescripcion(DESCRIPTION);
        RESTAURANT.setDireccion(ADDRESS);
        RESTAURANT.setImagen(IMAGE);
        RESTAURANT.setTurns(TURN_LIST);
        RESTAURANT.setMesas(BOARD_LIST);
        RESTAURANT.setReservas(RESERVATION_LIST);

        BoardRestDto.setId_BOARD(BOARD_ID);
        BoardRestDto.setNumber(NUMBER);
        BoardRestDto.setCapacity(CAPACITY);
        BoardRestDto.setRestaurant_Id(RESERVATION_ID);

        BoardJsonRest.setRestaurantId(RESERVATION_ID);
        BoardJsonRest.setNumeroMesa(CAPACITY);
        BoardJsonRest.setNumeroPersonas(NUMBER);

    }

    @Test
    public void getBoardTest() throws BookingExceptions {
        Mockito.when(boardRepository.findById(BOARD_ID)).thenReturn(OPTIONAL_Board);
        boardService.getBoard(BOARD_ID);
    }

    @Test(expected = BookingExceptions.class)
    public void getBoardNotFoundTest() throws BookingExceptions {
        Mockito.when(boardRepository.findById(BOARD_ID)).thenReturn(OPTIONAL_Board_Empty);
        boardService.getBoard(BOARD_ID);
        fail();
    }

    @Test
    public void deleteBoardId() throws BookingExceptions {
        boardRepository.deleteById(BOARD_ID);
        Mockito.verify(boardRepository).deleteById(BOARD_ID);
        boardService.deleteBoardById(BOARD_ID);
    }

    @Test
    public void listBoardsTest() throws BookingExceptions {
        Mockito.when(boardRepository.findAll()).thenReturn(Arrays.asList(Board));
        final List<BoardDto> response = boardService.listBoards();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertEquals(response.size(),1);
    }

    @Test
    public void addBoardTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(boardRepository.findById(BOARD_ID)).thenReturn(OPTIONAL_Board);
        Mockito.when(boardRepository.save(Mockito.any(BoardEntity.class))).thenReturn(new BoardEntity());
        boardService.addBoard(BoardJsonRest);
    }

    @Test(expected = BookingExceptions.class)
    public void addBoardTestInternalServerError() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(boardRepository.findById(BOARD_ID)).thenReturn(OPTIONAL_Board);

        Mockito.doThrow(new RuntimeException())
                .when(boardRepository)
                .save(Mockito.any(BoardEntity.class));
        boardService.addBoard(BoardJsonRest);
        fail();
    }

    @Test
    public void updateBoardTest() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(boardRepository.findById(BOARD_ID)).thenReturn(OPTIONAL_Board);
        Mockito.when(boardRepository.save(Mockito.any(BoardEntity.class))).thenReturn(new BoardEntity());
        boardService.updateBoard(BoardRestDto);
    }

    @Test(expected = BookingExceptions.class)
    public void updateBoardTestInternalServerError() throws BookingExceptions {
        Mockito.when(restaurantRepository.findById(RESERVATION_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(boardRepository.findById(BOARD_ID)).thenReturn(OPTIONAL_Board);

        Mockito.doThrow(new RuntimeException())
                .when(boardRepository)
                .save(Mockito.any(BoardEntity.class));
        boardService.updateBoard(BoardRestDto);
        fail();
    }

}
