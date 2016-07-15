package com.ge.predix.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ge.predix.service.SiteDRService;

@CrossOrigin
@RestController
public class DRController {
	
	@Autowired
	SiteDRService siteDRService;
	
	@RequestMapping("/getSiteDRData")
	public List<HashMap<String, Object>> getSiteDRData() throws Exception{
		return siteDRService.getSiteDRData();
	}
}
