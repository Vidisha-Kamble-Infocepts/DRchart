package com.ge.predix.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ge.predix.entity.DRPlotband;

@Service
public interface DRService {

	public List<HashMap<String, Object>> getSiteData();

	public List<DRPlotband> getAssetPlotbandData();
}
