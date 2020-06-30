package com.cervas.repository.helper.cliente;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cervas.model.Cliente;
import com.cervas.repository.filter.ClienteFilter;

public class ClientesImpl implements ClientesQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cliente> filter(ClienteFilter filtro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		//	Ordenação
		adicionarOrdenacao(criteria, pageable, builder, root);
		
		//	Adiciona filtros
		Predicate[] predicatesArray = criarResticoes(filtro, builder, root);
		
		criteria.where(predicatesArray);
		
		// Método de Paginação
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		
		return null;
	}


	//	Adiciona Ordenacao
	private void adicionarOrdenacao(CriteriaQuery<Cliente> criteria, Pageable pageable, CriteriaBuilder builder,
			Root<Cliente> root) {
		
		Sort sort = pageable.getSort();
		
		if(sort.isSorted()) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.orderBy(order.isAscending() ? builder.asc(root.get(property)) : builder.desc(root.get(property)));
		}
		
	}
	
	//	Cria Resticoes
	private Predicate[] criarResticoes(ClienteFilter filtro, CriteriaBuilder builder, Root<Cliente> root) {
		return null;
	}
	
	// Método de Paginação
	private void adicionarPaginacao(TypedQuery<Cliente> query, Pageable pageable) {
		
	}
	
}
