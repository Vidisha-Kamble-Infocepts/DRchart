package com.ge.predix.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ge.predix.entity.Site;
import com.ge.predix.repository.SiteImplRepository;
import com.ge.predix.util.DateUtil;

@Component
public class PDServiceImpl implements PDService{

	@Autowired
	public SiteImplRepository repository;

	@Override
	public List<HashMap<String, Object>> getSiteData(){
		final List<Site> results = repository.findAll();
		final List<HashMap<String, Object>> sitePeakDemand = new ArrayList();
		for (final Site factSiteDemand : results) {
			final HashMap<String, Object> instanceData = new HashMap<>();
			final HashMap<String, Long> siteDemand = new HashMap<>();
			siteDemand.put("Average Electrical Demand", factSiteDemand.getAssetaverageelectricaldemand());
			siteDemand.put("Estimated Electrical Peak", factSiteDemand.getSiteestimatedelectricalpeak());
			siteDemand.put("Billing Cycle Peak", factSiteDemand.getBillingperiodpeak());
			instanceData.put("time", DateUtil.getTimeInMillisecond(factSiteDemand.getSiteTime()));
			instanceData.put("values", siteDemand);
			sitePeakDemand.add(instanceData);
		}
		return sitePeakDemand;
	}

	@Override
	public HashSet<HashMap<String, Object>> findAllPeakPlotbands(){
		final HashSet<HashMap<String, Object>> sitePeakModelObject = new HashSet<>();
		final List<Object[]> buildingDemand = repository.findAllPeakPlotbands();
		final HashSet<String> dateWithoutTimeCollection=new HashSet<>();

		try {


			for (int i = 0; i < buildingDemand.size(); i++) {
				final Object object = buildingDemand.get(i);

				dateWithoutTimeCollection.add(DateUtil.extractDate(object));
			}

			for(final String dateWithoutTime:dateWithoutTimeCollection){
				HashMap<String, Object> assetDemand = new HashMap<>();
				final String startTimePeak=" 08:00:00.0 ";
				final String endTimePeak=" 22:00:00.0 ";
				final String dateWithStartTimePeak= dateWithoutTime.concat(startTimePeak);
				final String dateWithEndTimePeak= dateWithoutTime.concat(endTimePeak);

				if(dateWithStartTimePeak.contains(startTimePeak) && dateWithEndTimePeak.contains(endTimePeak))
				{
					assetDemand.put("plotband","PEAK");
					assetDemand.put("starttime",DateUtil.getTimeInMillisecond(DateUtil.getTimestamp(dateWithStartTimePeak)));
					assetDemand.put("endtime",DateUtil.getTimeInMillisecond(DateUtil.getTimestamp(dateWithEndTimePeak)));
					//instanceData.put("values", assetDemand);
					sitePeakModelObject.add(assetDemand);
				}

				final String startTimeOfPeak=" 22:00:00.0 ";
				final String endTimeOfPeak=" 23:59:59.0 ";
				final String dateWithStartTimeOfPeak= dateWithoutTime.concat(startTimeOfPeak);
				final String dateWithEndTimeOfPeak= dateWithoutTime.concat(endTimeOfPeak);
				if(dateWithStartTimeOfPeak.contains(startTimeOfPeak)&&dateWithEndTimeOfPeak.contains(endTimeOfPeak)){
					assetDemand = new HashMap<>();
					assetDemand.put("plotband","OFF PEAK");
					assetDemand.put("starttime",DateUtil.getTimeInMillisecond(DateUtil.getTimestamp(dateWithStartTimeOfPeak)));
					assetDemand.put("endtime",DateUtil.getTimeInMillisecond(DateUtil.getTimestamp(dateWithEndTimeOfPeak)));
					//hmapCt.put("values", hmap);
					sitePeakModelObject.add(assetDemand);
				}

				final String startTimeOffPeak=" 00:00:00.0 ";
				final String endTimeOffPeak=" 08:00:00.0 ";
				final String dateWithStartTimeOffPeak= dateWithoutTime.concat(startTimeOffPeak);
				final String dateWithEndTimeOffPeak= dateWithoutTime.concat(endTimeOffPeak);
				if(dateWithStartTimeOffPeak.contains(dateWithStartTimeOffPeak)&&dateWithEndTimeOffPeak.contains(dateWithEndTimeOffPeak)){
					assetDemand = new HashMap<>();
					assetDemand.put("plotband","OFF PEAK");
					assetDemand.put("starttime",DateUtil.getTimeInMillisecond(DateUtil.getTimestamp(dateWithStartTimeOffPeak)));
					assetDemand.put("endtime",DateUtil.getTimeInMillisecond(DateUtil.getTimestamp(dateWithEndTimeOffPeak)));
					//hashmapCt.put("values",hashmap);
					sitePeakModelObject.add(assetDemand);
				}
			}
			return sitePeakModelObject;
		}catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
