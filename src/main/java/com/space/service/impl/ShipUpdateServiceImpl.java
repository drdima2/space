package com.space.service.impl;

import com.space.entity.Ship;
import com.space.exception.BadRequestException;
import com.space.repository.ShipRepository;
import com.space.service.ShipSelectService;
import com.space.service.ShipUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipUpdateServiceImpl implements ShipUpdateService {

    @Autowired
    private ShipSelectService shipSelectService;

    @Autowired
    private ShipRepository shipRepository;


    @Override
    public Ship updateShip(Ship ship, Long id) {
        Ship shipUpdated = shipSelectService.findById(id);
        shipUpdated = updateShipObject(shipUpdated,ship);
        if (!shipUpdated.isShipContainRequiredParameters()) throw  new BadRequestException("update bad parameters");
        shipUpdated.setRating(shipUpdated.calculateRating().doubleValue());
        return shipRepository.save(shipUpdated);
    }

    private Ship updateShipObject(Ship shipUpdated, Ship ship){
        if (ship.getName() != null)
            shipUpdated.setName(ship.getName());

        if (ship.getPlanet() != null)
            shipUpdated.setPlanet(ship.getPlanet());

        if (ship.getShipType() != null)
            shipUpdated.setShipType(ship.getShipType());

        if (ship.getProdDate() != null)
            shipUpdated.setProdDate(ship.getProdDate());

        if (ship.getSpeed() != null)
            shipUpdated.setSpeed(ship.getSpeed());

        if (ship.getUsed() != null)
            shipUpdated.setUsed(ship.getUsed());

        if (ship.getCrewSize() != null)
            shipUpdated.setCrewSize(ship.getCrewSize());

        return shipUpdated;
    }
}
