package com.space.service.impl;

import com.space.entity.Ship;
import com.space.repository.ShipRepository;
import com.space.service.CreateShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;


@Service
public class CreateShipServiceImpl implements CreateShipService {

    @Autowired
    private ShipRepository shipRepository;


    @Override
    public Ship createShip(Ship ship) {



        BigDecimal rating = ship.calculateRating();
        ship.setRating(rating.doubleValue());
        return shipRepository.saveAndFlush(ship);
    }




}
