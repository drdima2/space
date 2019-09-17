package com.space.controller;

import com.space.entity.Ship;
import com.space.exception.BadRequestException;
import com.space.service.FindShipService;
import com.space.service.UpdateShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//import com.space.service.FindShipService;

@RestController
public class ShipUpdateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipUpdateController.class);

    @Autowired
    private UpdateShipService updateShipService;

    @RequestMapping(value = "/rest/ships/{id}",method = RequestMethod.POST)
    public Ship updateShip(@PathVariable("id") Long id, @RequestBody Ship shipReq){

        Ship shipUpdated = updateShipService.updateShip(shipReq,id);



        return shipUpdated;
    }





}
