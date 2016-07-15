package com.ge.predix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.KPIBoard;

public interface KPIBoardRepository extends CrudRepository<KPIBoard,Integer>{
	@Override
	public List<KPIBoard> findAll();
}
