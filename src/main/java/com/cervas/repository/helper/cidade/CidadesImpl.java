package com.cervas.repository.helper.cidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.cervas.model.Cidade;
import com.cervas.model.Cidade_;
import com.cervas.repository.filter.CidadeFilter;

public class CidadesImpl implements CidadesQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		//Adicionar Filtros
		Predicate[] predicateArray = criarRestricoes(cidadeFilter, builder, root);
		criteria.where(predicateArray);
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		
		adicionarResticoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, 10);
	}

	// Método de Paginação
	private void adicionarResticoesDePaginacao(TypedQuery<Cidade> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}

	private Predicate[] criarRestricoes(CidadeFilter cidadeFilter, CriteriaBuilder builder, Root<Cidade> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		if (cidadeFilter != null) {
			if (!StringUtils.isEmpty(cidadeFilter.getEstado())) {
				predicates.add(builder.equal(root.get(Cidade_.estado), cidadeFilter.getEstado()));
			}
			if (!StringUtils.isEmpty(cidadeFilter)) {
				predicates.add(builder.like(root.get(Cidade_.nome), "%" + cidadeFilter.getNome() + "%"));
			}
		}
		
		return (predicates.toArray(new Predicate[predicates.size()]));
	}

}
