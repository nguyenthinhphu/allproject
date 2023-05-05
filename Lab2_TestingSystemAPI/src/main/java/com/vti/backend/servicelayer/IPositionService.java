package com.vti.backend.servicelayer;

import java.util.List;

import com.vti.entity.Position;

public interface IPositionService {
	public List<Position> getAllPosition();

	public Position getPositionById(int id);

	public Position getPositionByName(String namePos);
}
