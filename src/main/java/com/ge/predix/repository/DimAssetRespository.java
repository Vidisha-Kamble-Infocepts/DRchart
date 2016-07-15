package com.ge.predix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ge.predix.entity.DimAsset;

public interface DimAssetRespository extends CrudRepository<DimAsset, Integer> {

	public List<DimAsset> findByBuildingId(int buildingId);
	public List<DimAsset> findByAssetType(String assettype);
	public DimAsset findByAssetId(int assetId);
	@Override
	public List<DimAsset> findAll();
}
