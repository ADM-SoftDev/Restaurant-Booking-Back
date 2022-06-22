package com.adm.restaurante.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.adm.restaurante.entity.TurnEntity;

import javax.transaction.Transactional;

@Repository
public interface TurnRepository extends JpaRepository<TurnEntity,Long> {
	
	Optional<TurnEntity> findById(Long id);
	Optional<TurnEntity> findByName(String name);

}
