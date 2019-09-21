package com.space.service.impl;

import com.space.entity.Ship;
import com.space.repository.ShipRepository;
import com.space.service.ShipCreateService;
import com.space.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;


@Service
public class ShipCreateServiceImpl implements ShipCreateService {

    @Autowired
    private ShipRepository shipRepository;


    @Override
    public Ship createShip(Ship ship) {
        ship.setProdDate(DateUtil.yearConvert(ship.getProdDate()));
        BigDecimal rating = ship.calculateRating();
        ship.setRating(rating.doubleValue());
        return shipRepository.saveAndFlush(ship);
    }




}
