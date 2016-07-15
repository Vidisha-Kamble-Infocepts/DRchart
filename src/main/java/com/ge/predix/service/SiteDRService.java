package com.ge.predix.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.sql.Timestamp;
import java.util.HashMap;

@Service
public interface SiteDRService {
	
	public List<HashMap<String, Object>> getSiteDRData();
}
