package com.ge.predix.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ge.predix.entity.KPIBoard;
import com.ge.predix.repository.KPIBoardRepository;

@Component
public class KPIBoardServiceImpl implements KPIBoardService{

	private static final Logger log = LoggerFactory.getLogger(KPIBoardServiceImpl.class);

	@Autowired
	KPIBoardRepository repository;

	@Override
	public List<KPIBoard> getKpiData() {
		List<KPIBoard> listFactSiteKpi = null;
		try {
			listFactSiteKpi = repository.findAll();
			final KPIBoard conEdKPI = new KPIBoard();
			conEdKPI.setKpiName("ConEd Utility Day AheadPeak Demand Forecast");

			try {
				final Long kpiValue = processPage("https://apps.coned.com/ESWeb/public/EnergyEfficiencyLoadForecast.aspx");
				conEdKPI.setValues(kpiValue);
			} catch (SQLException | IOException e) {
				log.error("Unable to obtain ConEd response ...");
				e.printStackTrace();
			}
			listFactSiteKpi.add(conEdKPI);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return listFactSiteKpi;
	}

	public Long processPage(String URL) throws SQLException, IOException {
		final Connection.Response response = Jsoup.connect(URL).execute();
		final Document doc = Jsoup.parse("<table>" + response.body() + "</table>");
		final Element tag = doc.getElementById("lblGreen");
		final Double kpivalue = Double.parseDouble(tag.text().substring(0, tag.text().indexOf("%")));
		System.out.println(kpivalue);
		return kpivalue.longValue();
	}

}
