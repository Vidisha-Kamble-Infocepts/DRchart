package com.ge.predix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.predix.entity.KPIBoard;
import com.ge.predix.service.KPIBoardServiceImpl;

@CrossOrigin
@RestController
public class KPIBoardController {

	@Autowired
	public KPIBoardServiceImpl service;


	@RequestMapping("/getKpiDemand")
	public List<KPIBoard> getKpiData()throws Exception{
		final List<KPIBoard> listFactSiteKpi =service.getKpiData();
		return listFactSiteKpi;
	}
}