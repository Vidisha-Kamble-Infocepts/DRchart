package com.ge.predix.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.service.PDService;

@CrossOrigin
@RestController
public class PeakDemandController {

	@Autowired
	public PDService service;

	@RequestMapping("/getSitePeakDemand")
	public List<HashMap<String, Object>> getSiteData() throws Exception {
		return service.getSiteData();
	}

	@RequestMapping("/getPeakPlotband")
	public HashSet<HashMap<String, Object>> findAllPeakPlotbands(){
		final HashSet<HashMap<String, Object>> listPeakPlotband=service.findAllPeakPlotbands();
		return listPeakPlotband;
	}

}

