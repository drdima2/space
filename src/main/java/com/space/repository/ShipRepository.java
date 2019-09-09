package com.space.repository;

import com.space.entity.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

//@Repository
@RepositoryDefinition(domainClass=Ship.class, idClass=Long.class)
public interface ShipRepository extends JpaRepository<Ship,Long> {



    List<Ship> findAll(Sort sort);
    //int countAllById();
    List<Ship> findByNameAndPlanet(String name, String planet);



    List<Ship> findByNameOrPlanet(
            String name,
            String planet

    );


    @Query("SELECT s FROM Ship s WHERE (" +
            "s.name like %:name% and " +
            "s.planet like %:planet% and " +
            "s.shipType = ANY or s.shipType = :shipType " +
            ") ")
    List<Ship> findByParameters(
            @Param("name") String name,
            @Param("planet") String planet,
            @Param("shipType") ShipType shipType


    );




}
