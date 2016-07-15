package com.ge.predix.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.entity.DRPlotband;
import com.ge.predix.service.BMService;

@CrossOrigin
@RestController
public class BatteryModelController{
	@Autowired
	public BMService service;

	@RequestMapping("/getSiteBatteryModel")
	public List<HashMap<String, Object>> getSiteData() throws Exception {
		return service.getSiteDataBattery();
	}


	@RequestMapping("/getAssetTypeBatteryModel")
	public List<HashMap<String, Object>> getAssetTypeData(
			@RequestParam(value = "assetType", defaultValue = "AC") String assetType) throws Exception {
		return service.getAssetTypeDataBattery(assetType);
	}

	@RequestMapping("/getAssetBatteryModel")
	public List<HashMap<String, Object>> getAssetData(@RequestParam(value = "assetId", defaultValue = "1") int assetId)
			throws Exception {
		return service.getAssetData(assetId);
	}

	@RequestMapping("/getBatteryPlotband")
	public List<DRPlotband> getBatteryPlotband(@RequestParam(value = "assetId", defaultValue = "1") int assetId) throws Exception {
		return service.getBatteryPlotband(assetId);
	}


}
