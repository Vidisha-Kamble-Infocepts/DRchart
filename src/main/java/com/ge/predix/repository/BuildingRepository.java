package com.ge.predix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.Building;

public interface BuildingRepository extends CrudRepository<Building, Integer> {

	@Query("from Building a where a.dimBuilding.id =?1 order by time asc")
	public List<Building> findByBuildingId(int buildingId);

	@Override
	List<Building> findAll();
}
