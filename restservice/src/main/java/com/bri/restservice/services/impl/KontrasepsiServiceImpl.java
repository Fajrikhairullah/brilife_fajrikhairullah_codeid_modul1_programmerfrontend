package com.bri.restservice.services.impl;

import com.bri.restservice.entities.Kontrasepsi;
import com.bri.restservice.exceptions.EntityNotFoundException;
import com.bri.restservice.repositories.KontrasepsiRepository;
import com.bri.restservice.services.KontrasepsiService;
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
public class KontrasepsiServiceImpl extends CommonServiceImpl<Kontrasepsi, Integer> implements KontrasepsiService{

    @Autowired
    private KontrasepsiRepository repository;
    
    @Override
    public Kontrasepsi save(Kontrasepsi entity) {
        return repository.save(entity);
    }

    @Override
    public Kontrasepsi removeById(Integer id) {
        Kontrasepsi entity = findById(id);
        repository.delete(entity);

        return entity;

    }

    @Override
    public Kontrasepsi findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<Kontrasepsi> findAll(Kontrasepsi entity, int page, int size, Sort.Direction direction) {
        Sort s = Sort.Direction.DESC.equals(direction) ? Sort.by("id").descending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return repository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, s));
    }

    
    @Override
    protected JpaRepository<Kontrasepsi, Integer> getRepository() {
        return repository;
    }
    
}
