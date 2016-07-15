package com.ge.predix.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.Building;
import com.ge.predix.repository.BuildingRepository;
import com.ge.predix.util.DateUtil;

@Component
public class ConsumptionServiceImpl implements ConsumptionService {

	@Autowired
	private BuildingRepository repository;

	@Override
	public List<HashMap<String, Object>> getBuildingData(int buildingId) {
		long prevHourConsumption=0;
		long maxHourConsumption=0;
		long maxHourValue=0;
		long prevHourValue=0;

		final List<Building> results = repository.findByBuildingId(buildingId);
		final List<HashMap<String, Object>> timeSeriesJSON = new ArrayList<>();
		for (final Building consumption : results) {
			final HashMap<String, Object> instanceData = new HashMap<>();
			final HashMap<String, Long> assetDemand = new HashMap<>();
			maxHourValue=DateUtil.getTimeInMillisecond(consumption.getTime());
			if(prevHourConsumption!=0){
				if(maxHourValue != prevHourValue){
					maxHourConsumption = (consumption.getSiteelectricalconsumption())-prevHourConsumption;
				}
			}
			prevHourValue=maxHourValue;
			prevHourConsumption = consumption.getSiteelectricalconsumption();
			if(maxHourConsumption!=0){
			instanceData.put("time", prevHourValue);
			assetDemand.put(getBuildingName(consumption),maxHourConsumption);
			instanceData.put("values", assetDemand);
			timeSeriesJSON.add(instanceData);
			}
		}
		return timeSeriesJSON;
	}

	@Override
	public List<HashMap<String, Object>> getSiteData() throws CloneNotSupportedException {

		final List<Building> results = repository.findAll();
		final List<HashMap<String, Object>> timeSeriesJSON = new ArrayList<>();
		final Map<Timestamp, List<Building>> timeSeriesData = new TreeMap<>();
		for (final Building consumption : results) {
			if (timeSeriesData.containsKey(consumption.getTime())) {
				timeSeriesData.get(consumption.getTime()).add(consumption);
			} else {
				final List<Building> consumptionList = new ArrayList<>();
				consumptionList.add(consumption);
				timeSeriesData.put(consumption.getTime(), consumptionList);
			}
		}

		final Map<Timestamp, List<Building>> targetTimeSeriesData = new TreeMap<>();
		List<Timestamp> timestamps = new ArrayList<>();
		for (Timestamp key : timeSeriesData.keySet()) {
			timestamps.add(key);
		}
		for (int i = 0 ; i < timestamps.size() ; i ++) {
			for (Timestamp key : timeSeriesData.keySet()) {
				if ( i < timestamps.size() - 1 && timestamps.get(i + 1) == key){
					List<Building> previousConsumption = new ArrayList<>(timeSeriesData.get(timestamps.get(i))) ;
					List<Building> currentConsumption = new ArrayList<>(timeSeriesData.get(timestamps.get(i + 1)));
					List<Building> deltaConsumption = getDeltaConsumption(previousConsumption, currentConsumption);
					targetTimeSeriesData.put(timestamps.get( i + 1), deltaConsumption);
				}
			}
		}

		for (final Timestamp key : targetTimeSeriesData.keySet()) {
			final HashMap<String, Object> instanceData = new HashMap<>();
			final HashMap<String, Long> consumptionMap = new HashMap<>();
			final List<Building> consumptionList = targetTimeSeriesData.get(key);
			for (final Building buildingConsumption : consumptionList) {
				consumptionMap.put(getBuildingName(buildingConsumption), buildingConsumption.getSiteelectricalconsumption());
			}
			instanceData.put("time", DateUtil.getTimeInMillisecond(key));
			instanceData.put("values", consumptionMap);
			timeSeriesJSON.add(instanceData);
		}
		return timeSeriesJSON;

	}


	private List<Building> getDeltaConsumption(List<Building> previousConsumption, List<Building> currentConsumption) throws CloneNotSupportedException {
		List<Building> deltaConsumption = new ArrayList<>();
		for (int i = 0; i < currentConsumption.size(); i++) {
			for (int j = 0; j < previousConsumption.size(); j++) {
				if (currentConsumption.get(i).getDimBuilding().getId() == previousConsumption.get(j).getDimBuilding()
						.getId()) {
					Building building = (Building)currentConsumption.get(i).clone();
					building.setSiteelectricalconsumption(currentConsumption.get(i).getSiteelectricalconsumption()
							- previousConsumption.get(j).getSiteelectricalconsumption());
					deltaConsumption.add(building);
					break;
				}
			}
		}
		deltaConsumption.addAll(getPreviousOnlyBuildings(currentConsumption, previousConsumption));
		return deltaConsumption;
	}

	private List<Building> getPreviousOnlyBuildings(List<Building> currentConsumption, List<Building> previousConsumption) {
		List<Integer> currentBuildings = new ArrayList<>();
		List<Building> previousOnlyBuildings = new ArrayList<>();
		for (Iterator iterator = currentConsumption.iterator(); iterator.hasNext();) {
			Building building = (Building) iterator.next();
			currentBuildings.add(building.getDimBuilding().getId());
		}
		for (int j = 0; j < previousConsumption.size(); j++) {
			boolean previousOnly = true;
			for (int i = 0; i < currentBuildings.size(); i++) {
				if (currentBuildings.get(i) == previousConsumption.get(j).getDimBuilding().getId()){
					previousOnly = false;
				}
			}
			if (previousOnly){
				previousOnlyBuildings.add(previousConsumption.get(j));
			}
		}
		return previousOnlyBuildings;
	}

	private String getBuildingName(Building bldgElement) {
		String name = bldgElement.getDimBuilding().getName();
		if (name.toLowerCase().contains("leadership exchange")){
			return "LE";
		}
		return name;
	}


}
