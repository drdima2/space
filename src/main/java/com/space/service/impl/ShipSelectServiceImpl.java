package com.space.service.impl;

import java.util.Calendar;
import com.space.controller.ShipOrder;
import com.space.entity.Ship;
import com.space.exception.BadRequestException;
import com.space.exception.NotFoundRequestException;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.service.ShipSelectService;
import com.space.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;


import java.util.*;

@Service

public class ShipSelectServiceImpl implements ShipSelectService {


    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;


    public Ship findById(Long id){
        if (id<=0) throw new BadRequestException("id must be a positive number");
        Ship ship;
        try {
            ship = shipRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundRequestException("this id doesn't exists");
        }
        return ship;
    }

    @Override
    public int countByParameters(Map<String,String> p) {
        List<Ship> results = shipRepository.findAll(getSpecification(p));
        return results.size();
    }



    @Override
    public List<Ship> findByParameters(Map<String,String> p) {
        int pageNumber = p.get("pageNumber")==null? 0 : Integer.valueOf(p.get("pageNumber"));
        int pageSize = p.get("pageSize")==null? 3 : Integer.valueOf(p.get("pageSize"));
        ShipOrder shipOrder = p.get("order")==null? ShipOrder.valueOf("ID") : ShipOrder.valueOf(p.get("order"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(shipOrder.getFieldName()));
        List<Ship> results = shipRepository.findAll(getSpecification(p),pageable).getContent();
        return results;
    }


    private Specification getSpecification(Map<String,String> p){
        Specification s = Specification.where( filterByName(p.get("name"))
                .and(filterByPlanet(p.get("planet"))))
                .and(filterByShipType(  p.get("shipType") ==null? null : ShipType.valueOf(p.get("shipType"))   ))
                .and(filterByDate(p.get("after") ==null? null : Long.valueOf(p.get("after")), p.get("before") ==null? null : Long.valueOf(p.get("before"))   ))
                .and(filterByCrew(p.get("minCrewSize") ==null? null : Integer.valueOf(p.get("minCrewSize")), p.get("maxCrewSize") ==null? null : Integer.valueOf(p.get("maxCrewSize"))   ))
                .and(filterBySpeed(p.get("minSpeed") ==null? null : Double.valueOf(p.get("minSpeed")), p.get("maxSpeed") ==null? null : Double.valueOf(p.get("maxSpeed"))   ))
                .and(filterByRating(p.get("minRating") ==null? null : Double.valueOf(p.get("minRating")), p.get("maxRating") ==null? null : Double.valueOf(p.get("maxRating"))   ))
                .and(filterByIsUsed(  p.get("isUsed") ==null? null : Boolean.valueOf(p.get("isUsed"))   ));

        return s;
    }


    private Specification<Ship> filterByName(String name){
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%" + name.trim() + "%");
    }

    private Specification<Ship> filterByPlanet(String planet) {
        return (root, query, cb) -> planet == null ? null : cb.like(root.get("planet"), "%" + planet.trim() + "%");
    }

    private Specification<Ship> filterByShipType(ShipType shipType) {
        return (root, query, cb) -> shipType == null ? null : cb.equal(root.get("shipType"), shipType);
    }

    private Specification<Ship> filterByDate(Long after, Long before) {
        return (root, query, cb) -> {
            if (after == null && before == null)
                return null;

            if (after == null) {
                Date beforeYear = DateUtil.yearConvert(new Date(before));
                return cb.lessThanOrEqualTo(root.get("prodDate"), beforeYear);
            }

            if (before == null) {
                Date afterYear = DateUtil.yearConvert(new Date(after));
                return cb.greaterThanOrEqualTo(root.get("prodDate"), afterYear);
            }
            Date beforeYear = DateUtil.yearConvert(new Date(before));
            Date afterYear = DateUtil.yearConvert(new Date(after));
            return cb.between(root.get("prodDate"), afterYear, beforeYear);
        };
    }



    private Specification<Ship> filterByCrew(Integer minCrewSize, Integer maxCrewSize) {
        return (root, query, cb) -> {
            if (minCrewSize == null && maxCrewSize == null)
                return null;

            if (minCrewSize == null) {
                return cb.lessThanOrEqualTo(root.get("crewSize"), maxCrewSize);
            }

            if (maxCrewSize == null) {
                return cb.greaterThanOrEqualTo(root.get("crewSize"), minCrewSize);
            }

            return cb.between(root.get("crewSize"), minCrewSize,maxCrewSize );
        };
    }



    private Specification<Ship> filterBySpeed(Double minSpeed, Double maxSpeed) {
        return (root, query, cb) -> {
            if (minSpeed == null && maxSpeed == null)
                return null;

            if (minSpeed == null) {
                return cb.lessThanOrEqualTo(root.get("speed"), maxSpeed);
            }

            if (maxSpeed == null) {
                return cb.greaterThanOrEqualTo(root.get("speed"), minSpeed);
            }

            return cb.between(root.get("speed"), minSpeed,maxSpeed );
        };
    }


    private Specification<Ship> filterByRating(Double minRating, Double maxRating) {
        return (root, query, cb) -> {
            if (minRating == null && maxRating == null)
                return null;

            if (minRating == null) {
                return cb.lessThanOrEqualTo(root.get("rating"), maxRating);
            }

            if (maxRating == null) {
                return cb.greaterThanOrEqualTo(root.get("rating"), minRating);
            }

            return cb.between(root.get("rating"), minRating,maxRating );
        };
    }

    private Specification<Ship> filterByIsUsed(Boolean isUsed) {
        return (root, query, cb) -> isUsed == null ? null : cb.equal(root.get("isUsed"), isUsed);
    }





}
