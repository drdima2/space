package com.space.service;

import com.space.entity.Ship;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CreateShipService {

    Ship  createShip(Ship ship);
}
