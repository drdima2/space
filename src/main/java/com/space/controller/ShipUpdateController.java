package com.space.controller;

import com.space.entity.Ship;
import com.space.service.ShipUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShipUpdateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipUpdateController.class);

    @Autowired
    private ShipUpdateService shipUpdateService;

    @RequestMapping(value = "/rest/ships/{id}",method = RequestMethod.POST)
    public Ship updateShip(@PathVariable("id") Long id, @RequestBody Ship shipReq){

        Ship shipUpdated = shipUpdateService.updateShip(shipReq,id);
        return shipUpdated;
    }





}
