package com.ge.predix.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.entity.DRPlotband;
import com.ge.predix.service.DRService;


@CrossOrigin
@RestController
public class DemandResponseController {

	@Autowired
	public DRService service;

	@RequestMapping("/getSiteDemand")
	public List<HashMap<String, Object>>  getSiteData() throws Exception {
		return service.getSiteData();
	}

	@RequestMapping("/getAssetPlotband")
	public List<DRPlotband> getAssetPlotbandData() throws Exception {
		return service.getAssetPlotbandData();
	}

}
