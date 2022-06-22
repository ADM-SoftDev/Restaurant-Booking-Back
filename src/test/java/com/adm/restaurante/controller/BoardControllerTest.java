package com.adm.restaurante.controller;

import com.adm.restaurante.dto.BoardDto;
import com.adm.restaurante.dto.BoardJsonRest;
import com.adm.restaurante.dto.BookingResponses;
import com.adm.restaurante.dto.RestaurantDto;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.BoardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class BoardControllerTest {

    @Mock
    private BoardService boardService;
    @InjectMocks
    BoardController boardController;

    private static final Long BOARD_ID = 1L;
    private static final Long CAPACITY = 1L;
    private static final Long NUMBER = 1L;
    private static final Long RESTAURANT_ID = 1L;

    BoardDto boardDto = new BoardDto();
    public static final List<BoardDto> RESTAURANT_LIST_BOARD = new ArrayList<>();
    BoardJsonRest boardJsonRest = new BoardJsonRest();

    private static final String SUCCESS_STATUS = "Success";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String SUCCESS_MESSAGE = "OK";
    private static final String MESSAGE_ADD = "BOARD ADD";
    private static final String MESSAGE_UPDATE = "BOARD UPDATE";
    private static final String MESSAGE_DELETE =  "SE HA ELIMINADO ID:" + BOARD_ID;


    @Before
    public void init() throws BookingExceptions {
        MockitoAnnotations.initMocks(this);

        boardDto.setId_BOARD(BOARD_ID);
        boardDto.setCapacity(CAPACITY);
        boardDto.setNumber(NUMBER);
        boardDto.setRestaurant_Id(RESTAURANT_ID);

        boardJsonRest.setRestaurantId(RESTAURANT_ID);
        boardJsonRest.setNumeroMesa(NUMBER);
        boardJsonRest.setNumeroPersonas(CAPACITY);

        Mockito.when(boardService.getBoard(BOARD_ID)).thenReturn(boardDto);
        Mockito.when(boardService.addBoard(boardJsonRest)).thenReturn(MESSAGE_ADD);
        Mockito.when(boardService.updateBoard(boardDto)).thenReturn(MESSAGE_UPDATE);
        Mockito.when(boardService.deleteBoardById(BOARD_ID)).thenReturn(MESSAGE_DELETE);
    }

    @Test
    public void findBoardByIdTest() throws BookingExceptions {
        final BookingResponses<BoardDto> responseDto = boardController.findBoardById(BOARD_ID);
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),boardDto );
    }

    @Test
    public void listarBoardTest() throws BookingExceptions {
        final BookingResponses<List<BoardDto>> responseDto = boardController.listarBoard();
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),RESTAURANT_LIST_BOARD );
    }

    @Test
    public void createBoardTest() throws BookingExceptions {
        final BookingResponses <String> responseDto = boardController.createBoard(boardJsonRest);
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),MESSAGE_ADD );
    }

    @Test
    public void updateBoardTest() throws BookingExceptions {
        final BookingResponses <String> responseDto = boardController.updateBoard(boardDto);
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),MESSAGE_UPDATE );
    }

    @Test
    public void eliminarBoardTest() throws BookingExceptions {
        final BookingResponses <String> responseDto = boardController.eliminarBoard(BOARD_ID);
        Assert.assertEquals(responseDto.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(responseDto.getCode(), SUCCESS_CODE);
        Assert.assertEquals(responseDto.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(responseDto.getData(),MESSAGE_DELETE );
    }

}
