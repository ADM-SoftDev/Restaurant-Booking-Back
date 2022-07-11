package com.adm.restaurante.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.adm.restaurante.entity.BoardEntity;

import javax.transaction.Transactional;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long>{
	
	Optional<BoardEntity> findById(Long id);

}
