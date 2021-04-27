package com.hescha.barbershop.service;


import com.hescha.barbershop.entity.MService;
import com.hescha.barbershop.repository.MServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MServiceService extends CrudImpl<MService> {

    public MServiceRepository repository;

    @Autowired
    public MServiceService(MServiceRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
