package com.space.repository;

import com.space.entity.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass=Ship.class, idClass=Long.class)
public interface ShipRepository extends
        JpaRepository<Ship,Long> , JpaSpecificationExecutor<Ship> {

}
