package com.space.controller;

import com.space.service.ShipDeleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class ShipDeleteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipDeleteController.class);

    @Autowired
    private ShipDeleteService shipDeleteService;

    @RequestMapping(value = "/rest/ships/{id}",method = RequestMethod.DELETE)
    public void updateShip(@PathVariable("id") Long id){
        shipDeleteService.deleteShip(id);

    }





}
