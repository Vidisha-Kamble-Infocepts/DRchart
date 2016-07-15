package com.ge.predix.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.Asset;

public interface AssetRepository extends CrudRepository<Asset,Integer>{

	@Query("select a from Asset a where a.dimAsset.assetId = ?1 order by assetTime asc")
	public List<Asset> findAssetById(final int assetId);

	@Query("select b.assetTime,sum(b.assetuppercontrollimit),sum(b.assetlowercontrollimit),sum(b.receivedsetpoint), sum(b.assetelectricaldemand) from DimAsset a join "
			+ "a.assets b " + "where a.assetType = ?1 "
			+ "group by b.assetTime,a.assetType order by b.assetTime asc")
	public List<Object[]> findAssetTypeBatteryModel(String assetType);

	@Query("select b.assetTime,sum(b.assetuppercontrollimit),sum(b.assetlowercontrollimit),sum(b.receivedsetpoint), sum(b.assetelectricaldemand) from DimAsset a join "
			+ "a.assets b " + "group by b.assetTime order by b.assetTime asc")
	public List<Object[]> findSiteLevelBatteryModel();

	@Query("select max(a.assetTime) from Asset a")
	public String findMaxDate();

	@Query("SELECT a FROM Asset a" + " where a.dimAsset.assetId IN :assetId" + " and a.assetTime "+ "BETWEEN :firstDate AND :lastDate")
	public List<Asset> findAssetByStartDate(final int assetId,Timestamp firstDate, Timestamp lastDate);


	@Query("select b.assetTime,sum(b.assetuppercontrollimit),sum(b.assetlowercontrollimit),sum(b.receivedsetpoint), sum(b.assetelectricaldemand) from DimAsset a join "
			+ "a.assets b " + "where (a.assetType IN :assetType"+" AND b.assetTime"+" BETWEEN :firstDate AND :lastDate) "+ " group by b.assetTime,a.assetType order by b.assetTime asc")
	public List<Object[]> findAssetTypeBatteryModelByDate(String assetType,Timestamp firstDate, Timestamp lastDate);

	@Query("select b.assetTime,sum(b.assetuppercontrollimit),sum(b.assetlowercontrollimit),sum(b.receivedsetpoint), sum(b.assetelectricaldemand) from DimAsset a "+" join "
			+ "a.assets b "+ " where (b.assetTime "+ "BETWEEN :firstDate AND :lastDate)" + " group by b.assetTime order by b.assetTime asc")
	public List<Object[]> findSiteLevelBatteryModelByDate(Timestamp firstDate, Timestamp lastDate);

}
