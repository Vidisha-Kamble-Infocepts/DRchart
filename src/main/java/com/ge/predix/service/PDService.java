package com.ge.predix.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PDService {

	public List<HashMap<String, Object>> getSiteData();

	public HashSet<HashMap<String, Object>> findAllPeakPlotbands();

}
