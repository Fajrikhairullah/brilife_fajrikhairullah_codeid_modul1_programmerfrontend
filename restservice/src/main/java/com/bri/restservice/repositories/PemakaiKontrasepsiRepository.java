package com.bri.restservice.repositories;

import com.bri.restservice.entities.PemakaiKontrasepsi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PemakaiKontrasepsiRepository extends JpaRepository<PemakaiKontrasepsi, Integer>, PemakaiKontrasepsiRepositoryCustom{
    
}
