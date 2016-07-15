package com.ge.predix.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ge.predix.entity.DRPlotband;
import com.ge.predix.entity.Site;
import com.ge.predix.repository.DRPlotbandRepository;
import com.ge.predix.repository.SiteImplRepository;
import com.ge.predix.util.DateUtil;

@Component
public class DRServiceImpl implements DRService {

	@Autowired
	public SiteImplRepository repository;
	

	@Autowired
	public DRPlotbandRepository plotBandRepository;

	@Override
	public List<HashMap<String, Object>> getSiteData(){
		final List<Site> results = repository.findAll();
		final List<HashMap<String, Object>> siteDemandResponse = new ArrayList<HashMap<String, Object>>();
		for (final Site factSiteDemand : results) {
			final HashMap<String, Object> instanceData = new HashMap<>();
			final HashMap<String, Long> siteDemand = new HashMap<>();

			instanceData.put("time", DateUtil.getTimeInMillisecond(factSiteDemand.getSiteTime()));
			//siteDemand.put("Lower Limit", factSiteDemand.getSiteLowercontrollimit());
			siteDemand.put("Capacity Committed", factSiteDemand.getOfferedcapacity());
			siteDemand.put("Realtime capacity", factSiteDemand.getRealtimecapacity());
			instanceData.put("values", siteDemand);
			siteDemandResponse.add(instanceData);
		}
		return siteDemandResponse;
	}

	@Override
	public List<DRPlotband> getAssetPlotbandData(){
		//final List<DRPlotband> results = plotBandRepository.findAll();
		
		final List<Timestamp[]> requestedPlotbands = plotBandRepository.findPlotband("REQUESTED");
		final List<Timestamp[]> undercontrolPlotbands = plotBandRepository.findPlotband("UNDER CONTROL");
		
		
		final JSONArray timeSeriesJSON = new JSONArray();
		for (final Object[] element : requestedPlotbands) {
			//final Timestamp factAssetPlotband = (Timestamp) element[0];
			final JSONObject instanceData = new JSONObject();
			final JSONObject assetDemand = new JSONObject();
			assetDemand.put("plotband", "REQUESTED");
			assetDemand.put("reportname", "REPORT 1");
			assetDemand.put("starttime", DateUtil.getTimeInMillisecond((Timestamp)element[0]));
			assetDemand.put("endtime", DateUtil.getTimeInMillisecond((Timestamp)element[1]));
			instanceData.put("values", assetDemand);

			timeSeriesJSON.add(instanceData);
		}
		
		for (final Object[] element : undercontrolPlotbands) {
			//final DRPlotband factAssetPlotband = (DRPlotband) element;
			final JSONObject instanceData = new JSONObject();
			final JSONObject assetDemand = new JSONObject();
			assetDemand.put("plotband", "UNDER CONTROL");
			assetDemand.put("reportname", "REPORT 1");
			assetDemand.put("starttime", DateUtil.getTimeInMillisecond((Timestamp)element[0]));
			assetDemand.put("endtime", DateUtil.getTimeInMillisecond((Timestamp)element[1]));
			instanceData.put("values", assetDemand);

			timeSeriesJSON.add(instanceData);
		}
		return timeSeriesJSON;
	}
}