package com.vti.backend.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.TblUser;

public interface IUserRepository extends JpaRepository<TblUser, Integer>, JpaSpecificationExecutor<TblUser>{

}
