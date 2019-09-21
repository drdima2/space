package com.space.controller;

import com.space.entity.Ship;
import com.space.service.ShipSelectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShipSelectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipSelectController.class);

    @Autowired
    private ShipSelectService shipSelectService;

    @RequestMapping(value = "/rest/ships",method = RequestMethod.GET)
    public List<Ship> getShips(@RequestParam Map<String,String> params){

        List<Ship> ships = shipSelectService.findByParameters(params);
        return ships;
    }


    @RequestMapping(value = "/rest/ships/count",method = RequestMethod.GET)
    public int getShipsCount(@RequestParam Map<String,String> params){
        return shipSelectService.countByParameters(params);
    }



    @RequestMapping(value = "/rest/ships/{id}",method = RequestMethod.GET)
    public Ship getShips(@PathVariable("id") Long id,Model model){

        Ship ship = shipSelectService.findById(id);
        return ship;
    }



}
