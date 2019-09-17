package com.space.service;

import com.space.entity.Ship;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface UpdateShipService {


    Ship updateShip(Ship ship, Long id);


}
