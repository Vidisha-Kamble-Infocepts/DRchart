package com.ge.predix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.ApplicationProperty;

public interface ApplicationPropertyRepository extends CrudRepository<ApplicationProperty, Integer>{    
    @Override
    public List<ApplicationProperty> findAll();
}
