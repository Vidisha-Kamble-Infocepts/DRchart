/*package com.ge.predix.service.csvreader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;



public class SuperCSVDemo {
	public static void main(String args[]){
		String csvFileName = "src/main/resources/sampleBldg.csv";
        System.out.println(readCSVFile(csvFileName));
	}
		public static List<String> readCSVFile(String path){
			SampleBldg sampleBldg = null;
			List<String> siteELectricalConsumption=new ArrayList<>();
			List<String> siteELectricalDemand=new ArrayList<>();
			CellProcessor[] processors = new CellProcessor[] {
			        new NotNull(), // DateTime
			        new NotNull(), // SiteElectricalConsumption
			        new NotNull(), // SiteElectricalDemand
			};
		ICsvBeanReader beanReader;
		try {
			beanReader = new CsvBeanReader(new FileReader(path),CsvPreference.STANDARD_PREFERENCE);
			String[] header = beanReader.getHeader(true);
			while ((sampleBldg = beanReader.read(SampleBldg.class, header, processors)) != null) {
				// deals with each bean read
				String eltConsumption = sampleBldg.getSiteElectricalConsumption();
				String electricalDemand=sampleBldg.getSiteElectricalDemand();
				String dateTime=sampleBldg.getDateTime();

				siteELectricalConsumption.add(eltConsumption);
				//siteELectricalDemand.add(electricalDemand);
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return siteELectricalConsumption;
		}
}
 */