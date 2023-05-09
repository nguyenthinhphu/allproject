package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.TblUser;
import com.vti.form.UserFilterForm;

public class UserSpecification{

public static Specification<TblUser> buildWhere(UserFilterForm filterForm) {
		
		Specification<TblUser> where = null;
		
		if (filterForm != null) {
			CustomSpecification fullName = new CustomSpecification("fullName", filterForm.getFullName());
			CustomSpecification groupId = new CustomSpecification("groupId", filterForm.getGroupId());
			
			where = Specification.where(fullName).and(groupId);
			
		}
		
		return where;
	}

}


class CustomSpecification implements Specification<TblUser> {

		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private String field;
		
		private Object value;

		

		public CustomSpecification(String field, Object value) {
			super();
			this.field = field;
			this.value = value;
		}



		@Override
		public Predicate toPredicate(Root<TblUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			if (field.equalsIgnoreCase("fullName")) {
				return criteriaBuilder.like(root.get("fullName"), "%" + value.toString() + "%");
			}
			
			if (field.equalsIgnoreCase("groupId")) {
				return criteriaBuilder.equal(root.get("group").get("groupId"), value.toString());
			}
			return null;
		}

}
