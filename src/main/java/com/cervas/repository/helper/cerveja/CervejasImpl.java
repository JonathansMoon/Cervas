package com.cervas.repository.helper.cerveja;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cervas.model.Cerveja;
import com.cervas.model.Cerveja_;
import com.cervas.repository.filter.CervejaFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class CervejasImpl implements CervejasQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {
        //Controla a Criteria
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        //Carrego a classe Cerveja e injeta na Criteria
        CriteriaQuery<Cerveja> criteria = builder.createQuery(Cerveja.class);
        Root<Cerveja> root = criteria.from(Cerveja.class);
        // Ordenação - Precisa ser adicioado logo após a declaração do root
        adicionarOrdenacao(criteria, pageable, builder, root);
 
        // Adiciona as Restrições
        Predicate[] predicates = criarRestricoes(cervejaFilter, builder, root);
        criteria.where(predicates);
        
        TypedQuery<Cerveja> query = manager.createQuery(criteria);

        //Chamada de método de Paginação
        adicionarResticoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(cervejaFilter));
    }

    // Método de Ordenação
    private void adicionarOrdenacao(CriteriaQuery<Cerveja> criteria, Pageable pageable, CriteriaBuilder builder, Root<Cerveja> root) {
		//pageable.getSort recebe os dados   http referentes a ordenação da tabela
    	Sort sort = pageable.getSort();

		if (sort.isSorted()) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.orderBy(order.isAscending() ? builder.asc(root.get(property)) : builder.desc(root.get(property)));
		}
		
	}

	// Método de Paginação
    private void adicionarResticoesDePaginacao(TypedQuery<Cerveja> query, Pageable pageable) {
        //Paginação
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistro);
        query.setMaxResults(totalRegistrosPorPagina);
    }
    
    
    //Metodo numero total de registros
    private Long total(CervejaFilter cervejaFilter) {     
    	//Controla a Criteria
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        //Carrego a classe Cerveja e injeta na Criteria
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cerveja> root = criteria.from(Cerveja.class);

        //Restrições
        Predicate[] predicates = criarRestricoes(cervejaFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private Predicate[] criarRestricoes(CervejaFilter cervejaFilter, CriteriaBuilder builder, Root<Cerveja> root) {
        //Já que o retorno requer um array pradicate[], preciso de uma lista de predicates para tranformar em array
        List<Predicate> predicates = new ArrayList<>();
        if(cervejaFilter != null) {
            if(!StringUtils.isEmpty(cervejaFilter.getSku())) {
                predicates.add(builder.equal(root.get(Cerveja_.sku), cervejaFilter.getSku()));
            }
            
            if(!StringUtils.isEmpty(cervejaFilter.getNome())) {
            	predicates.add(builder.like(root.get(Cerveja_.nome), "%" + cervejaFilter.getNome() + "%"));
            }
            
            if(!StringUtils.isEmpty(cervejaFilter.getEstilo())) {
            	predicates.add(builder.equal(root.get(Cerveja_.estilo), cervejaFilter.getEstilo()));
            }
            
            if(!StringUtils.isEmpty(cervejaFilter.getSabor())) {
            	predicates.add(builder.equal(root.get(Cerveja_.sabor), cervejaFilter.getSabor()));
            }
            if(!StringUtils.isEmpty(cervejaFilter.getOrigem())) {
            	predicates.add(builder.equal(root.get(Cerveja_.origem), cervejaFilter.getOrigem()));
            }
            if(!StringUtils.isEmpty(cervejaFilter.getValorDe())) {
            	predicates.add(builder.greaterThanOrEqualTo(root.get(Cerveja_.valor), cervejaFilter.getValorDe()));
            }
            if(!StringUtils.isEmpty(cervejaFilter.getValorAte())) {
            	predicates.add(builder.lessThanOrEqualTo(root.get(Cerveja_.valor), cervejaFilter.getValorAte()));
            }
            
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}