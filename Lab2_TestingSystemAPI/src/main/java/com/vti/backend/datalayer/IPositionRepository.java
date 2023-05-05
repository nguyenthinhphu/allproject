package com.vti.backend.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Position;

public interface IPositionRepository extends JpaRepository<Position, Integer> {

	public Position findByName(String name);
}
