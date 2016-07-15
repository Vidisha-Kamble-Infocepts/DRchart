package com.ge.predix.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {

	@Override
	@Query("SELECT s from Site s order by site_time asc")
	public List<Site> findAll();

	@Query("select distinct s.siteTime from Site s")
	public List<Object[]> findAllPeakPlotbands();
	
	@Query("select min(s.siteTime) from Site s")
	public String findSiteDRDate();
	@Query("SELECT s FROM Site s" + " where s.siteTime "+ "BETWEEN :firstDate AND :lastDate")
	public List<Site> findSiteDRDataByTime(Timestamp firstDate, Timestamp lastDate);
}
