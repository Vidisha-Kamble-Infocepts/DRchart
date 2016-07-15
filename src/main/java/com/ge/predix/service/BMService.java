package com.ge.predix.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ge.predix.entity.DRPlotband;

@Service
public interface BMService{

	public List<HashMap<String, Object>> getAssetData(int assetId);
	public List<HashMap<String, Object>> getSiteDataBattery();
	public List<HashMap<String, Object>> getAssetTypeDataBattery(String assetType);
	public List<DRPlotband> getBatteryPlotband(int assetId);

}
