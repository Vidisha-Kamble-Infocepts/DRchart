package com.ge.predix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.entity.ApplicationProperty;
import com.ge.predix.entity.DimAsset;
import com.ge.predix.repository.ApplicationPropertyRepository;
import com.ge.predix.repository.DimAssetRespository;

@CrossOrigin
@RestController
public class ApplicationPropertyController {

	@Autowired
	private DimAssetRespository assetInventory;
	
	@Autowired
	private ApplicationPropertyRepository applicationProperty;

	@RequestMapping("/getAssetInventory")
	public List<DimAsset> getBuildingData() throws Exception {
		final List<DimAsset> results = assetInventory.findAll();
		// JPA not doing lazy implementation
		for (final DimAsset buildingAssetMapping2 : results) {
			final DimAsset buildingAssetMapping = buildingAssetMapping2;
			buildingAssetMapping.setAssets(null);
		}
		return results;
	}

	@RequestMapping("/getApplicationProperties")
	public List<ApplicationProperty> getApplicationProperties(){
		return applicationProperty.findAll();
	}
}
