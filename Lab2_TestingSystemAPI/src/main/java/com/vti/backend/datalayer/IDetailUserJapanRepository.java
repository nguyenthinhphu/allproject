package com.vti.backend.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.TblDetailUserJapan;

public interface IDetailUserJapanRepository  extends JpaRepository<TblDetailUserJapan, Integer>, JpaSpecificationExecutor<TblDetailUserJapan>{

}