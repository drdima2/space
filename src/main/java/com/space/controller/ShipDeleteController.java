package com.space.controller;

import com.space.entity.Ship;
import com.space.service.DeleteShipService;
import com.space.service.UpdateShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import com.space.service.FindShipService;

@RestController
public class ShipDeleteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipDeleteController.class);

    @Autowired
    private DeleteShipService deleteShipService;

    //@ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/rest/ships/{id}",method = RequestMethod.DELETE)
    public void updateShip(@PathVariable("id") Long id){
        deleteShipService.deleteShip(id);

    }





}
