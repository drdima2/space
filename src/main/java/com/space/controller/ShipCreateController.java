package com.space.controller;


import com.space.entity.Ship;
import com.space.exception.BadRequestException;
import com.space.service.ShipCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ShipCreateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipCreateController.class);

    @Autowired
    private ShipCreateService shipCreateService;

    @RequestMapping(value = "/rest/ships",method = RequestMethod.POST)
    public Ship createShip(@Valid @RequestBody Ship ship, BindingResult bindingResult){

        if (ship.getUsed()==null) ship.setUsed(false);
        if (bindingResult.hasErrors()){
            throw new BadRequestException("bad request");
        }
        if (!ship.isShipContainRequiredParameters()){
            throw new BadRequestException("bad request");
        }



        Ship newShip = shipCreateService.createShip(ship);
        return newShip;
    }


}
