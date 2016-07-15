package com.ge.predix.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ge.predix.entity.Site;
import com.ge.predix.repository.SiteImplRepository;
import com.ge.predix.util.DateUtil;
import java.lang.Math;

@Component
public class SiteDRServiceImpl implements SiteDRService{
	
	final Logger logger = Logger.getLogger(SiteDRServiceImpl.class.getName());
	
	@Autowired
	SiteImplRepository siteImplRepository;
	
	@Override
	public List<HashMap<String, Object>> getSiteDRData() {
		logger.info("Inside getSiteDRData()");
		try {
			final String siteDRDate = siteImplRepository.findSiteDRDate();
			logger.info("SiteDRDate:"+siteDRDate);
			
			final List<HashMap<String, Object>> siteDRObject = new ArrayList<>();
			String startDRDate="2016-06-24 13:00:00.0";
			String endDRDate="2016-06-24 18:00:00.0";
			
			String drEventDate="2016-06-24 03:00:00.0";

			
			final List<Site> listSiteDR = siteImplRepository.findSiteDRDataByTime(DateUtil.getTimestamp(startDRDate),DateUtil.getTimestamp(endDRDate));
//			final List<Site> listSiteDR = siteImplRepository.findSiteDRDataByTime(DateUtil.substractHours(drEventDate),DateUtil.addHours(drEventDate));
			
			final List<HashMap<String, Object>> siteDR = new ArrayList<HashMap<String, Object>>();

			for (final Site factSiteDR : listSiteDR) {
				final HashMap<String, Object> instanceData = new HashMap<>();
				final HashMap<String, Long> siteDREvent = new HashMap<>();
				instanceData.put("time", DateUtil.getTimeInMillisecond(factSiteDR.getSiteTime()));
				siteDREvent.put("Expected Demand", factSiteDR.getSiteLowercontrollimit());
				siteDREvent.put("Actual Demand", factSiteDR.getSiteelectricaldemand());
				siteDREvent.put("Target Demand", Math.abs(factSiteDR.getSiteLowercontrollimit()-factSiteDR.getOfferedcapacity()));
				siteDREvent.put("Dispatchable Capacity", factSiteDR.getRealtimecapacity());
				instanceData.put("values", siteDREvent);
				siteDR.add(instanceData);
			}
			return siteDR;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}



