package com.orange.otheatre.otheatre.service;

import com.orange.otheatre.otheatre.entities.Hall;
import com.orange.otheatre.otheatre.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    @Autowired
    HallRepository hallRepository;

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public Hall findByHallName(String hallName) {
        return hallRepository.findByHallName(hallName);
    }

}
