package com.ge.predix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ge.predix.entity.KPIBoard;

@Service
public interface KPIBoardService {

	public List<KPIBoard> getKpiData();

}
