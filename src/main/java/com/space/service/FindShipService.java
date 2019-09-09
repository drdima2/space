package com.space.service;

import com.space.entity.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface FindShipService {


    //List<Ship> findAll();
    List<Ship> findAll(Sort sort);
    Ship findById(Long id);
    int countAllShips();

    List<Ship> findByParameters(Map<String,String> params);

}
