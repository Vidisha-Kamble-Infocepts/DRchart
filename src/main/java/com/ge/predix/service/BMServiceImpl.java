package com.ge.predix.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.Asset;
import com.ge.predix.entity.DRPlotband;
import com.ge.predix.repository.AssetImplRepository;
import com.ge.predix.repository.DRPlotbandRepository;
import com.ge.predix.util.DateUtil;

@Component
public class BMServiceImpl implements BMService{
	final Logger logger = Logger.getLogger(BMServiceImpl.class.getName());
	//	@Autowired
	//	AssetRepository repository;

	@Autowired
	AssetImplRepository assetImplRepository;

	@Autowired
	public DRPlotbandRepository plotBandRepository;

	@Override
	public List<HashMap<String, Object>> getAssetData(int assetId) {
		try {
			logger.info("Inside getAssetData()");
			final String maxDate = assetImplRepository.findMaxDate();
			logger.info("MaxDate is"+maxDate);
			final List<Asset> listFactAssetDemandImpl =	assetImplRepository.findAssetByStartDate(assetId, DateUtil.getPreviousDate(maxDate, 1), DateUtil.getPreviousDate(maxDate, 0));

			//final List<Asset> listFactAssetDemand = repository.findAssetById(assetId);

			final List<HashMap<String, Object>> assetBatteryModel = new ArrayList<HashMap<String, Object>>();

			for (final Asset factAssetDemand : listFactAssetDemandImpl) {

				final HashMap<String, Object> instanceData = new HashMap<>();
				final HashMap<String, Long> assetDemand = new HashMap<>();
				instanceData.put("time", DateUtil.getTimeInMillisecond(factAssetDemand.getAssetTime()));
				assetDemand.put("Actual Usage", factAssetDemand.getAssetelectricaldemand());
				assetDemand.put("Requested Usage", factAssetDemand.getReceivedsetpoint());
				assetDemand.put("Upper Limit", factAssetDemand.getAssetuppercontrollimit());
				assetDemand.put("Lower Limit", factAssetDemand.getAssetlowercontrollimit());
				instanceData.put("values", assetDemand);
				assetBatteryModel.add(instanceData);
			}
			//System.out.println("BatteryModel @ asset: " + assetBatteryModel.size());
			return assetBatteryModel;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getSiteDataBattery() {
		try {

			logger.info("Inside getSiteDataBattery()");

			final String maxDate = assetImplRepository.findMaxDate();

			logger.info("Maxdate is:"+maxDate);

			final List<HashMap<String, Object>> siteBatteryModelObject = new ArrayList<>();

			final List<Object[]> buildingDemand = assetImplRepository.findSiteLevelBatteryModelByDate(DateUtil.getPreviousDate(maxDate, 1), DateUtil.getPreviousDate(maxDate, 0));

			//final List<Object[]> buildingDemand = repository.findSiteLevelBatteryModel();

			for (int i = 0; i < buildingDemand.size(); i++) {
				final Object[] objects = buildingDemand.get(i);
				final List<Object> fields = Arrays.asList(objects);
				final HashMap<String, Object> instanceData = new HashMap<>();
				final HashMap<String, Long> assetDemand = new HashMap<>();
				instanceData.put("time", DateUtil.getTimeInMillisecond((Timestamp) fields.get(0)));
				assetDemand.put("Actual Usage", (Long) fields.get(4));
				assetDemand.put("Requested Usage", (Long) fields.get(3));
				assetDemand.put("Upper Limit", (Long) fields.get(1));
				assetDemand.put("Lower Limit", (Long) fields.get(2));
				instanceData.put("values", assetDemand);
				siteBatteryModelObject.add(instanceData);

			}
			//System.out.println("BatteryModel @ Site: " + siteBatteryModelObject.size());
			return siteBatteryModelObject;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getAssetTypeDataBattery(String assetType) {
		try {
			logger.info("Inside getAssetTypeDataBattery()");
			final String maxDate = assetImplRepository.findMaxDate();
			logger.info("MaxDate is:"+maxDate);
			final List<HashMap<String, Object>> assetTypeBatteryObject = new ArrayList<HashMap<String, Object>>();
			final List<Object[]> buildingDemand = assetImplRepository.findAssetTypeBatteryModelByDate(assetType, DateUtil.getPreviousDate(maxDate, 1), DateUtil.getPreviousDate(maxDate, 0));

			for (int i = 0; i < buildingDemand.size(); i++) {
				final Object[] objects = buildingDemand.get(i);
				final List<Object> fields = Arrays.asList(objects);
				final HashMap<String, Object> jsonObject = new HashMap<>();
				final HashMap<String, Long> assetDemand = new HashMap<>();
				jsonObject.put("time", DateUtil.getTimeInMillisecond((Timestamp) fields.get(0)));
				assetDemand.put("Actual Usage", (Long) fields.get(4));
				assetDemand.put("Requested Usage", (Long) fields.get(3));
				assetDemand.put("Upper Limit", (Long) fields.get(1));
				assetDemand.put("Lower Limit", (Long) fields.get(2));
				jsonObject.put("values", assetDemand);
				assetTypeBatteryObject.add(jsonObject);
			}
			//System.out.println("BatteryModel @ asset type : " + assetTypeBatteryObject.size());
			return assetTypeBatteryObject;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DRPlotband> getBatteryPlotband(int assetId) {
		logger.info("Inside BatteryPlotband");
		logger.info("Asset Id Battery Plot  : "+ assetId);
		final String maxDate = assetImplRepository.findMaxDate();
		Long endTime = DateUtil.getTimeInMillisecond(DateUtil.getPreviousDate(maxDate, 0));
		Long startTime = DateUtil.getTimeInMillisecond(DateUtil.getPreviousDate(maxDate, 1));

		final List<DRPlotband> results = plotBandRepository.findAll();
		final List<DRPlotband> plotBands = new ArrayList<DRPlotband>();
		logger.info("Results Size : "+results.size());
		final JSONArray timeSeriesJSON = new JSONArray();
		for (final Object element : results) {
			final DRPlotband factAssetPlotband = (DRPlotband) element;
			if(factAssetPlotband.getAssetId() == assetId && factAssetPlotband.getPlotband().equalsIgnoreCase("UNDER CONTROL") && DateUtil.getTimeInMillisecond(factAssetPlotband.getStarttime()) >= startTime){
				final JSONObject instanceData = new JSONObject();
				final JSONObject assetDemand = new JSONObject();
				assetDemand.put("plotband", factAssetPlotband.getPlotband());
				assetDemand.put("reportname", factAssetPlotband.getReportname());
				assetDemand.put("starttime", DateUtil.getTimeInMillisecond(factAssetPlotband.getStarttime()));
				assetDemand.put("endtime", DateUtil.getTimeInMillisecond(factAssetPlotband.getEndtime()));
				instanceData.put("values", assetDemand);
				plotBands.add(factAssetPlotband);
				timeSeriesJSON.add(instanceData);
			}
		}


		for (int i =0;i<plotBands.size();i++) {
			final Object element = plotBands.get(i);

			final DRPlotband factAssetPlotband = (DRPlotband) element;

			final JSONObject instanceData = new JSONObject();
			final JSONObject assetDemand = new JSONObject();
			assetDemand.put("plotband", "NO CONTROL");
			assetDemand.put("reportname", "Report1");
			assetDemand.put("starttime", startTime);
			assetDemand.put("endtime", DateUtil.getTimeInMillisecond(factAssetPlotband.getStarttime()));
			instanceData.put("values", assetDemand);
			startTime = DateUtil.getTimeInMillisecond(factAssetPlotband.getEndtime());
			timeSeriesJSON.add(instanceData);

			//for last plot band
			if(i == plotBands.size()-1){
				final JSONObject instanceDataLast = new JSONObject();
				final JSONObject assetDemandLast = new JSONObject();
				assetDemandLast.put("plotband", "NO CONTROL");
				assetDemandLast.put("reportname", "Report1");
				assetDemandLast.put("starttime", startTime);
				assetDemandLast.put("endtime", endTime);
				instanceDataLast.put("values", assetDemandLast);
				timeSeriesJSON.add(instanceDataLast);
				startTime = null;
				endTime = null;

			}

		}
		logger.info("Response Size : "+timeSeriesJSON.size());
		return timeSeriesJSON;
	}

}
