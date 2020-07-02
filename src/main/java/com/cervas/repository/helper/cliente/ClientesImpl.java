package com.cervas.repository.helper.cliente;

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

import com.cervas.model.Cliente;
import com.cervas.model.Cliente_;
import com.cervas.repository.filter.ClienteFilter;

public class ClientesImpl implements ClientesQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Cliente> filtrar(ClienteFilter parametrosDoFiltro, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		//	Ordenação
		adicionarOrdenacao(criteria, pageable, builder, root);
		
		//	Adiciona filtros
		Predicate[] predicatesArray = criarRestricoes(parametrosDoFiltro, builder, root);
		
		criteria.where(predicatesArray);
		
		// Método de Paginação
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(parametrosDoFiltro));
	}

	private Long total(ClienteFilter parametrosDoFiltro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		Predicate[] predicates = criarRestricoes(parametrosDoFiltro, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
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
	private Predicate[] criarRestricoes(ClienteFilter parametrosDoFiltro, CriteriaBuilder builder, Root<Cliente> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		if (parametrosDoFiltro != null) {
			if (!StringUtils.isEmpty(parametrosDoFiltro.getNome())) {
				predicates.add(builder.like(root.get(Cliente_.nome), "%" + parametrosDoFiltro.getNome() + "%"));
			}
			if (!StringUtils.isEmpty(parametrosDoFiltro.getCpfOuCnpj())) {
				predicates.add(builder.like(root.get(Cliente_.cpfOuCnpj), "%" + parametrosDoFiltro.getCpfOuCnpj() + "%"));
			}
		}	
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	// Método de Paginação
	private void adicionarPaginacao(TypedQuery<Cliente> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPaginacao = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginacao;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPaginacao);
		
	}
	
}
