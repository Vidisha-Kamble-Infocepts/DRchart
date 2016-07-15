package com.ge.predix.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.service.ConsumptionServiceImpl;

@CrossOrigin
@RestController
public class ConsumptionController {

	@Autowired
	public ConsumptionServiceImpl service;

	@RequestMapping("/getSiteConsumption")
	public List<HashMap<String, Object>>  getSiteData(@RequestParam(value = "assetType", defaultValue = "") String assetType)
			throws Exception {
		return service.getSiteData();
	}

	@RequestMapping("/getBuildingConsumption")
	public List<HashMap<String, Object>> getBuildingData(@RequestParam(value = "buildingId", defaultValue = "2") int buildingId,
			@RequestParam(value = "assetType", defaultValue = "") String assetType) throws Exception {
		return service.getBuildingData(buildingId);
	}
}
