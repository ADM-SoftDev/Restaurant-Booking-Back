package com.adm.restaurante.service.impl;

import com.adm.restaurante.dto.BoardJsonRest;
import com.adm.restaurante.entity.RestaurantEntity;
import com.adm.restaurante.exceptions.InternalServerErrorException;
import com.adm.restaurante.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adm.restaurante.dto.BoardDto;
import com.adm.restaurante.entity.BoardEntity;
import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.exceptions.NotFoundException;
import com.adm.restaurante.repository.BoardRepository;
import com.adm.restaurante.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	public static final ModelMapper modelMaper = new ModelMapper();

	private BoardEntity getBoardEntity(Long boardId) throws BookingExceptions {
		return boardRepository.findById(boardId)
				.orElseThrow(() -> new NotFoundException("ST-404-1", "NUM_ID_BOARD_NOT_FOUND"));
	}


	@Override
	public BoardDto getBoard(Long boardId) throws BookingExceptions {
		return modelMaper.map(getBoardEntity(boardId),BoardDto.class);
	}

	@Override
	public String deleteBoardById(Long boardId) throws BookingExceptions {
		boardRepository.deleteById(boardId);
		LOGGER.info("Localizado eliminado el Board",boardId);
		return "SE HA ELIMINADO ID:" + boardId;
	}

	@Override
	public List<BoardDto> listBoards() throws BookingExceptions {
		final List<BoardEntity> listBoard = boardRepository.findAll();
		return listBoard.stream().map(service -> modelMaper.map(service,BoardDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public String addBoard(BoardJsonRest addBoard) throws BookingExceptions {
		final RestaurantEntity restaurante = restaurantRepository.findById(addBoard.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_RESTAURANT_NOT_FOUND"));

		final BoardEntity board = new BoardEntity();
		board.setCapacidad(addBoard.getNumeroPersonas());
		board.setNumero(addBoard.getNumeroMesa());
		board.setRestaurante(restaurante);
		try {
			boardRepository.save(board);
		}catch(final Exception e) {
			LOGGER.error("INTERNAL SERVER ERROR", e);
			throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ALMACENAR_LOS_DATOS");
		}
		return "BOARD ADD";
	}


	public String updateBoard(BoardDto updateBoard) throws BookingExceptions {
		final BoardDto boardtId = this.getBoard(updateBoard.getId_BOARD());
		final RestaurantEntity restaurante = restaurantRepository.findById(updateBoard.getRestaurant_Id())
				.orElseThrow(() -> new NotFoundException("ST-404-1", "ID_RESTAURANT_NOT_FOUND"));
		if(boardtId!=null){
			final BoardEntity board = new BoardEntity();
			board.setID_MESA(updateBoard.getId_BOARD());
			board.setRestaurante(restaurante);
			board.setNumero(updateBoard.getNumber());
			board.setCapacidad(updateBoard.getCapacity());
			try {
				boardRepository.save(board);
				return "BOARD UPDATE";
			} catch (final Exception e) {
				LOGGER.error("INTERNAL SERVER ERROR", e);
				throw new InternalServerErrorException("ST-500-1", "ERROR_AL_ACTUALIZAR_LOS_DATOS");
			}

		}else{
			return "BOARD NO FOUNT";
		}
	}
}
