package com.bri.restservice.services.impl;

import com.bri.restservice.entities.PemakaiKontrasepsi;
import com.bri.restservice.exceptions.EntityNotFoundException;
import com.bri.restservice.models.PemakaiKontrasepsiSummary;
import com.bri.restservice.repositories.PemakaiKontrasepsiRepository;
import com.bri.restservice.services.PemakaiKontrasepsiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class PemakaiKontrasepsiServiceImpl extends CommonServiceImpl<PemakaiKontrasepsi, Integer> implements PemakaiKontrasepsiService{

    @Autowired
    private PemakaiKontrasepsiRepository repository;
    
   @Override
    public PemakaiKontrasepsi save(PemakaiKontrasepsi entity) {
        return repository.save(entity);
    }

    @Override
    public PemakaiKontrasepsi removeById(Integer id) {
        PemakaiKontrasepsi entity = findById(id);
        repository.delete(entity);

        return entity;

    }

    @Override
    public PemakaiKontrasepsi findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException();
        });
    }

    @Override
    public Page<PemakaiKontrasepsi> findAll(PemakaiKontrasepsi entity, int page, int size, Sort.Direction direction) {
        Sort s = Sort.Direction.DESC.equals(direction) ? Sort.by("id").descending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return repository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, s));

    }

    @Override
    public List<PemakaiKontrasepsiSummary> pemakaiKontrasepsiSummary() {
        return repository.pemakaiKontrasepsiSummary();
    }
    
    @Override
    protected JpaRepository<PemakaiKontrasepsi, Integer> getRepository() {
        return repository;
    }

}
