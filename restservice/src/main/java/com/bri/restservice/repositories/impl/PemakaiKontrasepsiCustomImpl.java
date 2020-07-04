package com.bri.restservice.repositories.impl;

import com.bri.restservice.entities.PemakaiKontrasepsi;
import com.bri.restservice.models.PemakaiKontrasepsiSummary;
import com.bri.restservice.repositories.PemakaiKontrasepsiRepositoryCustom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

public class PemakaiKontrasepsiCustomImpl implements PemakaiKontrasepsiRepositoryCustom{

    @Autowired
    private EntityManager eManager;
    
    @Override
    public List<PemakaiKontrasepsiSummary> pemakaiKontrasepsiSummary() {
        CriteriaBuilder builder = eManager.getCriteriaBuilder();
        CriteriaQuery<PemakaiKontrasepsiSummary> criteria = builder.createQuery(PemakaiKontrasepsiSummary.class);
        Root<PemakaiKontrasepsi> root = criteria.from(PemakaiKontrasepsi.class);
        
        criteria.multiselect(root.get("propinsi").get("propinsi"), builder.sum(root.get("jumlahPemakai")))
                .groupBy(root.get("propinsi"), root.get("kontrasepsi"));
        return eManager.createQuery(criteria).getResultList();
    }
    
}
