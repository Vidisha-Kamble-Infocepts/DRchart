package com.ge.predix.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.DRPlotband;

public interface DRPlotbandRepository extends CrudRepository<DRPlotband,Integer>{
	@Override
	public List<DRPlotband> findAll();
	
		
	@Query("select min(a.starttime),max(a.endtime) from DRPlotband a where plotband = ? GROUP BY DATE(a.starttime)")
	public List<Timestamp[]> findPlotband(final String name);

	
}
