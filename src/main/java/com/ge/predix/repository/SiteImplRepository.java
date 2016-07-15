package com.ge.predix.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.Site;

@Component
public class SiteImplRepository implements SiteRepository{
	@Autowired
	SiteRepository siteRepository;

	@PersistenceContext
	EntityManager em = null;

	final Logger logger = Logger.getLogger(SiteImplRepository.class.getName());

	@Override
	public List<Site> findSiteDRDataByTime(Timestamp firstDate, Timestamp lastDate) {
		logger.info("Inside findSiteDRDataByTime");
		final List<Site> results = em.createQuery(
				"SELECT s FROM Site s" +" where s.siteTime "+ "BETWEEN :firstDate AND :lastDate order by s.siteTime asc")
				.setParameter("firstDate", firstDate)
				.setParameter("lastDate", lastDate)
				.getResultList();

		logger.info("First Date: "+firstDate+"Last Date:"+lastDate+"Result is:"+results.size());
		return results;
	}


	@Override
	public String findSiteDRDate() {
		return siteRepository.findSiteDRDate();
	}


	@Override
	public <S extends Site> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Site> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Site findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterable<Site> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Site entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Iterable<? extends Site> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Site> findAll() {
		return siteRepository.findAll();
	}


	@Override
	public List<Object[]> findAllPeakPlotbands() {
		return siteRepository.findAllPeakPlotbands();
	}

	
}
