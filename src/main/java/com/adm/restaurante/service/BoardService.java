package com.adm.restaurante.service;

import com.adm.restaurante.dto.BoardDto;
import com.adm.restaurante.dto.BoardJsonRest;
import com.adm.restaurante.exceptions.BookingExceptions;

import java.util.List;

public interface BoardService {
	
	BoardDto getBoard(Long boardId)throws BookingExceptions;

	public String deleteBoardById(Long boardId)throws BookingExceptions;

	public List<BoardDto> listBoards() throws BookingExceptions;

	public String addBoard(BoardJsonRest addBoard)throws BookingExceptions;

	public String updateBoard(BoardDto updateBoard)throws BookingExceptions;

}
