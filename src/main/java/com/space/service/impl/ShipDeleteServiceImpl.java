package com.space.service.impl;

import com.space.exception.BadRequestException;
import com.space.exception.NotFoundRequestException;
import com.space.repository.ShipRepository;
import com.space.service.ShipDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipDeleteServiceImpl implements ShipDeleteService {

    @Autowired
    private ShipRepository shipRepository;


    @Override
    public void deleteShip(Long id) {
        if (id <=0 ) throw new BadRequestException("bad request");
        if (shipRepository.existsById(id)) {
            shipRepository.deleteById(id);
        } else throw new NotFoundRequestException("id not found");
    }


}
