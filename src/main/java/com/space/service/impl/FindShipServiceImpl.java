package com.space.service.impl;

import com.space.entity.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.service.FindShipService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class FindShipServiceImpl implements FindShipService {


    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;




    @Override
    public List<Ship> findAll(Sort sort) {
        return shipRepository.findAll(sort);
    }

    public Ship findById(Long id){
        return shipRepository.findById(id).get();
    }

    @Override
    public int countAllShips() {
        int size = findAll(new Sort("id")).size();

        return size;
    }

    @Override
    public List<Ship> findByNameAndPlanet(String name, String planet) {
        return shipRepository.findByNameAndPlanet(name,planet);
    }

    @Override
    public List<Ship> findByParameters(Map<String,String> p) {

        return shipRepository.findByParameters(
                Optional.ofNullable(p.get("name")).orElse("_"),
                Optional.ofNullable(p.get("planet")).orElse("_"),
                Optional.ofNullable(ShipType.valueOf(p.get("shipType"))).orElse(null)


        );
    }
}
