package com.bri.restservice.services;

import com.bri.restservice.entities.PemakaiKontrasepsi;
import com.bri.restservice.models.PemakaiKontrasepsiSummary;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface PemakaiKontrasepsiService {
    
    public PemakaiKontrasepsi save(PemakaiKontrasepsi entity);

    public PemakaiKontrasepsi removeById(Integer id);

    public PemakaiKontrasepsi findById(Integer id);

    public Page<PemakaiKontrasepsi> findAll(PemakaiKontrasepsi entity, int page, int size, Sort.Direction direction);
    
    public List<PemakaiKontrasepsiSummary> pemakaiKontrasepsiSummary();
    
}
