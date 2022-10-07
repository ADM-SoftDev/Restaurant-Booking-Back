package com.adm.restaurante.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adm.restaurante.entity.RestaurantEntity;

import javax.transaction.Transactional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>{

	Optional<RestaurantEntity> findById(Long id);
	Optional<RestaurantEntity> findByName(String nameRestaurant);
	@Query("SELECT REST FROM RestaurantEntity REST")
	List<RestaurantEntity> findRestaurantes();
	@Modifying
	@Transactional
	Optional<RestaurantEntity> deleteByName(String name);
}
