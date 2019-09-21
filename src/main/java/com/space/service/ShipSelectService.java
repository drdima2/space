package com.space.service;

import com.space.entity.Ship;

import java.util.List;
import java.util.Map;

public interface ShipSelectService {
    Ship findById(Long id);
    int countByParameters(Map<String,String> params);
    List<Ship> findByParameters(Map<String,String> params);

}
