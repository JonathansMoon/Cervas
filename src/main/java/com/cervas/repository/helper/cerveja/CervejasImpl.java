package com.cervas.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.cervas.model.Cerveja;
import com.cervas.repository.filter.CervejaFilter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

public class CervejasImpl implements CervejasQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cerveja> filtrar(CervejaFilter filtro) {

        if(filtro != null) {
            //Contruo a Criteria
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            //Carrego a classe Cerveja e injeto na Criteria
            CriteriaQuery<Cerveja> criteriaQuery = criteriaBuilder.createQuery(Cerveja.class);
            if(!StringUtils.isEmpty(filtro.getSku())) {
                criteriaQuery.add(Restrictions.eq("sku", filtro.getSku()));
                
            }
        }


        return null;
    }
}