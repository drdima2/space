package com.space.controller;

import com.space.entity.Counter;
import com.space.entity.Ship;
import com.space.model.ShipType;
//import com.space.service.FindShipService;
import com.space.service.FindShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PublicDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicDataController.class);

    @Autowired
    private FindShipService findShipService;

    @RequestMapping(value = "/rest/ships",method = RequestMethod.GET)
//    public List<Ship> getShips(@RequestParam Optional<String> name, @RequestParam Optional<String> planet){
    public List<Ship> getShips(@RequestParam Map<String,String> params){
//        LOGGER.info(p.toString());
//        LOGGER.info(p.get("name"));
//        LOGGER.info(p.get("planet"));
//        LOGGER.info(p.get("shipType"));

        //List<Ship> ships = findShipService.findAll(new Sort("id"));
        List<Ship> ships = findShipService.findByParameters(params);
        return ships;

    }


    @RequestMapping(value = "/rest/ships/{id}",method = RequestMethod.GET)
    public Ship getShips(@PathVariable("id") Long id,Model model){

        //Ship ship1 = new Ship(14L,"Scorpio E-X-1","Mars",ShipType.MERCHANT,new Date(32966812329671L), false, 0.03, 682, 0.40);
        //if (id==14) return ship1;
        Ship ship = findShipService.findById(id);
        return ship;
    }


    @RequestMapping(value = "/rest/ships/count",method = RequestMethod.GET)
    public int getShipsCount(Model model){
        //Counter counter = new Counter(56);
        return findShipService.countAllShips();




    }
}
