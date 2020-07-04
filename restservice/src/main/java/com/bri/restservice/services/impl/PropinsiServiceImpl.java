package com.bri.restservice.services.impl;

import com.bri.restservice.entities.Propinsi;
import com.bri.restservice.exceptions.EntityNotFoundException;
import com.bri.restservice.repositories.PropinsiRepository;
import com.bri.restservice.services.PropinsiService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class PropinsiServiceImpl extends CommonServiceImpl<Propinsi, Integer> implements PropinsiService{
    
    @Autowired
    private PropinsiRepository repository;
    
    @Override
    public Propinsi save(Propinsi entity) {
        return repository.save(entity);
    }

    @Override
    public Propinsi removeById(Integer id) {
        Propinsi entity = findById(id);
        repository.delete(entity);

        return entity;

    }

    @Override
    public Propinsi findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<Propinsi> findAll(Propinsi entity, int page, int size, Sort.Direction direction) {
        Sort s = Sort.Direction.DESC.equals(direction) ? Sort.by("id").descending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return repository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, s));
    }

    @Override
    protected JpaRepository<Propinsi, Integer> getRepository() {
        return repository;
    }
    
    

    
}
