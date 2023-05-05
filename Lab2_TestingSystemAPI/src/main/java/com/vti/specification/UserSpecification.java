package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.TblUser;

public class UserSpecification implements Specification<TblUser>{

	private static final long serialVersionUID = 1L;

	private String field;
	private String operator;
	private Object value;
	
	
	public UserSpecification(String field, String operator, Object value) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
	
	@Override
	public Predicate toPredicate(Root<TblUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		if (operator.equalsIgnoreCase("=")) {
			if (field.equalsIgnoreCase("group")) {
				return criteriaBuilder.equal(root.get(field).get("groupId"), value.toString());
			} else {
				return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
			}
		}
		return null;
	}

}
