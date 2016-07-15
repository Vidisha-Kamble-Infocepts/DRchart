package com.ge.predix.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.Asset;

@Component
public class AssetImplRepository implements AssetRepository{
	@Autowired
	AssetRepository assetRepository;

	@PersistenceContext
	EntityManager em = null;

	final Logger logger = Logger.getLogger(AssetImplRepository.class.getName());

	@Override
	public List<Asset> findAssetByStartDate(final int assetId,Timestamp firstDate, Timestamp lastDate) {
		logger.info("Inside findAssetByStartDate");
		final List<Asset> results = em.createQuery(
				"SELECT a FROM Asset a" +" where a.dimAsset.assetId IN :assetId" + " and a.assetTime "+ "BETWEEN :firstDate AND :lastDate  order by a.assetTime asc")
				.setParameter("assetId", assetId)
				.setParameter("firstDate", firstDate)
				.setParameter("lastDate", lastDate)
				.getResultList();

		logger.info("Asset Id:"+assetId+"First Date: "+firstDate+"Last Date:"+lastDate+"Result is:"+results.size());
		return results;
	}


	@Override
	public List<Object[]> findAssetTypeBatteryModelByDate(String assetType, Timestamp firstDate, Timestamp lastDate) {
		logger.info("Inside findAssetTypeBatteryModelByDate");

		final String query = "select b.assetTime,sum(b.assetuppercontrollimit),sum(b.assetlowercontrollimit),sum(b.receivedsetpoint), sum(b.assetelectricaldemand) from DimAsset a join "
				+ "a.assets b " + "where (a.assetType IN :assetType"+" AND b.assetTime"+" BETWEEN :firstDate AND :lastDate) "+ " group by b.assetTime,a.assetType order by b.assetTime asc";
		final List<Object[]> results = em.createQuery(
				query)
				.setParameter("assetType", assetType)
				.setParameter("firstDate", firstDate)
				.setParameter("lastDate", lastDate)
				.getResultList();

		logger.info("Asset Type level Query: Battery Model: " + query);
		logger.info("Asset Type:"+assetType+"First Date: "+firstDate+"Last Date:"+lastDate+"Result is:"+results.size());

		return results;
	}

	@Override
	public List<Object[]> findSiteLevelBatteryModelByDate(Timestamp firstDate, Timestamp lastDate) {
		logger.info("Inside findSiteLevelBatteryModelByDate");
		final List<Object[]> results = em.createQuery(
				"select b.assetTime,sum(b.assetuppercontrollimit),sum(b.assetlowercontrollimit),sum(b.receivedsetpoint), sum(b.assetelectricaldemand) from DimAsset a " + " join "
						+ "a.assets b "+ " where (b.assetTime "+ "BETWEEN :firstDate AND :lastDate)" + "group by b.assetTime order by b.assetTime asc")
				.setParameter("firstDate", firstDate)
				.setParameter("lastDate", lastDate)
				.getResultList();
		logger.info("First Date: "+firstDate+"Last Date:"+lastDate+"Result is:"+results.size());
		return results;
	}

	@Override
	public List<Asset> findAssetById(int assetId) {

		return assetRepository.findAssetById(assetId);
	}



	@Override
	public List<Object[]> findAssetTypeBatteryModel(String assetType) {
		return assetRepository.findAssetTypeBatteryModel(assetType);
	}



	@Override
	public List<Object[]> findSiteLevelBatteryModel() {
		return assetRepository.findSiteLevelBatteryModel();
	}



	@Override
	public String findMaxDate() {

		return assetRepository.findMaxDate();
	}

	@Override
	public <S extends Asset> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Asset> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asset findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Asset> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Asset> findAll(Iterable<Integer> ids) {
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
	public void delete(Asset entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Asset> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
