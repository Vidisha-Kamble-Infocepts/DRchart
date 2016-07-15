package com.ge.predix.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ConsumptionService {

	public List<HashMap<String, Object>> getBuildingData(int buildingId);
	public List<HashMap<String, Object>> getSiteData() throws CloneNotSupportedException;
}
