package com.vti.backend.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.MstGroup;

public interface IGroupRepository extends JpaRepository<MstGroup, Integer>, JpaSpecificationExecutor<MstGroup>{

	public MstGroup findByGroupName(String name);
}
