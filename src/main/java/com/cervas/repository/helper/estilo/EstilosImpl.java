package com.cervas.repository.helper.estilo;

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
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.cervas.model.Estilo;
import com.cervas.model.Estilo_;
import com.cervas.repository.filter.EstiloFilter;

public class EstilosImpl implements EstilosQueries{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Estilo> filtrar(EstiloFilter parametrosDoFiltro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Estilo> criteria  = builder.createQuery(Estilo.class);
		Root<Estilo> root = criteria.from(Estilo.class);
		
		adicionarOrdenacao(criteria, pageable, builder, root);
		
		Predicate[] predicatesArray = criarResticoes(parametrosDoFiltro, builder, root);
		
		criteria.where(predicatesArray);
		
		// Método de Paginação
		TypedQuery<Estilo> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(parametrosDoFiltro));
	}

	private Long total(EstiloFilter parametrosDoFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Estilo> root = criteria.from(Estilo.class);
		
		Predicate[] predicates = criarResticoes(parametrosDoFiltro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	// Método de Paginação
	private void adicionarPaginacao(TypedQuery<Estilo> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPaginacao = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginacao;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPaginacao);
		
	}

	// Método de Ordenação
	private void adicionarOrdenacao(CriteriaQuery<Estilo> criteria, Pageable pageable, CriteriaBuilder builder,
			Root<Estilo> root) {
		
		Sort sort = pageable.getSort();
		
		if (sort.isSorted()) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			System.out.println(">>> : " + sort);
			criteria.orderBy(order.isAscending() ? builder.asc(root.get(property)) :  builder.desc(root.get(property)));
		}
		
	}

	private Predicate[] criarResticoes(EstiloFilter parametrosDoFiltro, CriteriaBuilder builder, Root<Estilo> root) {
		
		// Cria um arrayList e guarda em uma lista de Predicates
		List<Predicate> predicates = new ArrayList<>();
		if (parametrosDoFiltro != null) {
			if (!StringUtils.isEmpty(parametrosDoFiltro.getNome())) {
				predicates.add(builder.like(root.get(Estilo_.nome), "%" + parametrosDoFiltro.getNome() + "%" ));
			}
		}
		// Tranforma restrições em array e retorna
		return predicates.toArray(new Predicate[predicates.size()]);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// Fazer uma Pesquisa simples sem ordenação, paginação ou total	
	
//	@Override
//	public List<Estilo> filtrar(EstiloFilter parametrosDoFiltro) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Estilo> criteria  = builder.createQuery(Estilo.class);
//		Root<Estilo> root = criteria.from(Estilo.class);
//		
//		
//		List<Predicate> predicates = new ArrayList<>();
//		if (parametrosDoFiltro != null) {
//			if(!StringUtils.isEmpty(parametrosDoFiltro.getNome())) {
//				  predicates.add(builder.equal(root.get(Estilo_.nome), parametrosDoFiltro.getNome()));
//			}
//		}
//		Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
//		criteria.where(predicatesArray);
//		return manager.createQuery(criteria).getResultList();
//	}
	
}
