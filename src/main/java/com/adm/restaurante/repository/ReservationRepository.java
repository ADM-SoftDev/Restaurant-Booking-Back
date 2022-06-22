package com.adm.restaurante.repository;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.adm.restaurante.entity.ReservationEntity;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{
	
	Optional<ReservationEntity> findById(Long id);
	Optional<ReservationEntity> findByLocator(String localizador);
	@Modifying
	@Transactional
	Optional<ReservationEntity> deleteByLocator(String localizador);
	Optional<ReservationEntity> findByTurnAndRestaurantId(String turn, Long id);

}
